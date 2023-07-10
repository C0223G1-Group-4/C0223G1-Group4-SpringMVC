package com.example.case_study.controller;

import com.example.case_study.model.ChairFlight;
import com.example.case_study.service.chair_service.ChairService;
import com.example.case_study.service.chairflight_service.IChairFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("chair_flight")
public class ChairFlightController {

    @Autowired
    ChairService chairService;
    @Autowired
    private IChairFlightService chairFlightService;
    @GetMapping("")
    public String getList(Model model){
//        List<ChairFlight> list = chairFlightService.getList(2);
        model.addAttribute("chairs",chairService.chairs());
        return "chair/seat";
    }

    @PostMapping("/create")
    public String create(@RequestParam("chair") List<ChairFlight> chairFlightList ){
        for (ChairFlight c: chairFlightList
             ) {
            c.setStatusChair(true);
            this.chairFlightService.update(c);
        }
        return "redirect:/chair_flight";
    }
}
