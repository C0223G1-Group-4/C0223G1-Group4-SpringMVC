package com.example.case_study.controller;

import com.example.case_study.service.post_service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ControllerTest {
    @Autowired
    private IPostService postService;
    @GetMapping("")
    public String show(@PageableDefault(size = 8) Pageable pageable, Model model){
        model.addAttribute("post",postService.findAll(pageable));

        return "home/index";
    }
}
