package com.example.case_study.controller;

import com.example.case_study.dto.PostDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Post;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.employees_service.IEmployeesService;
import com.example.case_study.service.post_service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IEmployeesService employeesService;
    @GetMapping("")
    public String listPost(Principal principal, Model model){
        model.addAttribute("posts",postService.findAll());
        model.addAttribute("acc",accountService.findByEmail(principal.getName()));
        return "post/list_post";
    }
    @GetMapping("/create")
    public String showFormCreate(Model model){
        model.addAttribute("postDto",new PostDto());
        return "/post/create_post";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult, Principal principal,RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("postDto",postDto);
            return "post/create_post";
        }
        AccountUser accountUser = accountService.findByEmail(principal.getName());
        Post post = new Post();
        BeanUtils.copyProperties(postDto,post);
        post.setEmployees(employeesService.findByIdAccount(accountUser.getId()));
        postService.create(post);
        redirectAttributes.addFlashAttribute("msg","Create success");
        return "redirect:/post";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable Integer id, Model model){
        if (postService.findById(id)==null){
            return "redirect:/post";
        }
        model.addAttribute("post",postService.findById(id));
        return "post/update_post";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("post",postDto);
            return "post/update_post";
        }
        Post post = new Post();
        BeanUtils.copyProperties(postDto,post);
        postService.update(post);
        return "redirect:/post";
    }
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Integer id, RedirectAttributes redirectAttributes, Model model){
        if (postService.findById(id)==null){
            redirectAttributes.addFlashAttribute("msgErr","Post not found.");
            return "redirect:/post";
        }
        model.addAttribute("postt",postService.findById(id));
        return "home/index";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "deletePost",required = false) Integer id, RedirectAttributes redirectAttributes){
        if (postService.findById(id) == null){
            redirectAttributes.addFlashAttribute("msgErr","Post not found.");
            return "redirect:/post";
        }
        redirectAttributes.addFlashAttribute("msg","Delete success");
        postService.delete(id);
        return "redirect:/post";
    }
}
