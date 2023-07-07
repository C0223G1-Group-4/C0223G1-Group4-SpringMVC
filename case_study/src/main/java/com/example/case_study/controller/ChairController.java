package com.example.case_study.controller;

import com.example.case_study.model.Chair;
import com.example.case_study.repository.IChairRepository;
import com.example.case_study.service.chair_service.IChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chair")
public class ChairController {
    @Autowired
    private IChairService chairService;
    @GetMapping("")
    public String showList(Model model){
       model.addAttribute("list1",chairService.getList(1));
        model.addAttribute("list2",chairService.getList(2));
        model.addAttribute("list3",chairService.getList(3));
        model.addAttribute("list4",chairService.getList(4));
        model.addAttribute("list5",chairService.getList(5));
        model.addAttribute("list6",chairService.getList(6));
        model.addAttribute("list7",chairService.getList(7));
        model.addAttribute("list8",chairService.getList(8));
        model.addAttribute("list9",chairService.getList(9));
        return "chair/chair";
    }
}
