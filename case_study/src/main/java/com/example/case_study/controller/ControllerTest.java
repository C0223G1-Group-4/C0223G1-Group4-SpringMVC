package com.example.case_study.controller;

import com.example.case_study.model.ChairFlight;
import com.example.case_study.service.post_service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
@SessionAttributes("listChair")
public class ControllerTest {

    @Autowired
    private IPostService postService;

    @ModelAttribute("listChair")
    public List<ChairFlight> init(){
        return new ArrayList<ChairFlight>();
    }
    @GetMapping("")
    public String show(Model model ){
        model.addAttribute("post",postService.findAll());
        return "home/index";
    }
}
