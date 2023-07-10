package com.example.case_study.controller;

import com.example.case_study.service.post_service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){
       model.addAttribute("post",postService.findById(id));
       return "post/detail_post";
    }
}
