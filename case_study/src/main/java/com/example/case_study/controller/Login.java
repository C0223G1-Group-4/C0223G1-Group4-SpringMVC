package com.example.case_study.controller;

import com.example.case_study.dto.AccountUserDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class Login {
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
    @GetMapping("/login")
    public String formLogin(Model model) {
            model.addAttribute("accountDto", new AccountUserDto());
            return "loginPage";
    }


    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        return "index";
    }

    @GetMapping("/400")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "400Page";
    }
}
