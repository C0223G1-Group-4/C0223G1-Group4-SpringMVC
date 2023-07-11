package com.example.case_study.controller;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.ChairFlight;
import com.example.case_study.model.Passengers;
import com.example.case_study.repository.IRoleUser;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.booking_ticket.IBookingTicketService;
import com.example.case_study.service.chairflight_service.IChairFlightService;
import com.example.case_study.service.passengers_service.IPassengersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class BookingTicketController {
    @Autowired
    private IBookingTicketService bookingTicketService;
    @Autowired
    private IPassengersService passengersService;
    @Autowired
    private IChairFlightService chairFlightService;

    @GetMapping("")
    public String showTicket(HttpServletRequest request, Model model,@SessionAttribute List<ChairFlight> listChair){
        String email = request.getUserPrincipal().getName();
        Passengers passengers = passengersService.findByEmail(email);
        model.addAttribute("passengers",passengers);
        return "information_ticket/information_ticket";
    }
}
