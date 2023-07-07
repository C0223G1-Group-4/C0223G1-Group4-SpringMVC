package com.example.case_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class BookingTicketController {

    @GetMapping("")
    public String showTicket(){
        return "information_ticket/information_ticket";
    }
}
