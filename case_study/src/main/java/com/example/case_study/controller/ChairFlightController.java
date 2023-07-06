package com.example.case_study.controller;

import com.example.case_study.model.ChairFlight;
import com.example.case_study.service.chairflight_service.IChairFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("chair_flight")
public class ChairFlightController {
    @Autowired
    private IChairFlightService chairFlightService;
    @GetMapping("")
    public String getList(Model model){
        List<ChairFlight> list = chairFlightService.getList(2);
        model.addAttribute("list",list);
        return "chair_flight/chair_flight";
    }

}
