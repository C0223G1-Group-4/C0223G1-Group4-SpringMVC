package com.example.case_study.controller;

import com.example.case_study.model.AccountUser;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.employees_service.IEmployeesService;
import com.example.case_study.service.passengers_service.IPassengersService;
import com.example.case_study.service.post_service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("")
public class ControllerTest {
    @Autowired
    private IPostService postService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPassengersService passengersService;
    @Autowired
    private IEmployeesService employeesService;
    @GetMapping("")
    public String show(@PageableDefault(size = 3, sort = "datePost", direction = Sort.Direction.DESC) Pageable pageable,Principal principal, Model model){
        model.addAttribute("post",postService.findAll());
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        if (principal != null){
            AccountUser accountUser = accountService.findByEmail(principal.getName());
            model.addAttribute("acc", accountUser);
            model.addAttribute("post", postService.findAll());
            if (accountUser.getRoleUser().getName().equals("ROLE_Customer")) {
                model.addAttribute("info", passengersService.findByIdAccount(accountUser.getId()));
                return "home/index";
            } else if (accountUser.getRoleUser().getName().equals("ROLE_Employee")) {
                model.addAttribute("info", employeesService.findByIdAccount(accountUser.getId()));
                return "home/index";
            } else {
                model.addAttribute("info", employeesService.findByIdAccount(accountUser.getId()));
                return "home/index";
            }
        }
        return "home/index";
    }
}
