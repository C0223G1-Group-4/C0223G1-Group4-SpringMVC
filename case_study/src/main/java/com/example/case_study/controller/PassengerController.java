package com.example.case_study.controller;

import com.example.case_study.dto.PassengerDto;
import com.example.case_study.service.passengers_service.IPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private IPassengersService iPassengersService;

    @GetMapping()
    public String showListPassenger(@PageableDefault(value = 5,sort = "id", direction = Sort.Direction.DESC)
                                        Pageable pageable, @RequestParam(value = "search",defaultValue = "")
                                        String search, Model model){
        model.addAttribute("passenger", iPassengersService.findByPassengers(search, pageable));
        model.addAttribute("search", search);
        return "/list_passenger";
    }
}
