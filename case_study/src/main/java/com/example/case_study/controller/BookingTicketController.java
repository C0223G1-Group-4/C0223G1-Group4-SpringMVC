package com.example.case_study.controller;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.Passengers;
import com.example.case_study.repository.IRoleUser;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.booking_ticket.IBookingTicketService;
import com.example.case_study.service.passengers_service.IPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/ticket")
public class BookingTicketController {
    @Autowired
    private IBookingTicketService bookingTicketService;
    @Autowired
    private IPassengersService passengersService;

    @GetMapping("")
    public String showTicket(HttpServletRequest request, Model model){
        String email = request.getUserPrincipal().getName();
        Passengers passengers = passengersService.findByEmail(email);
        BookingTicket bookingTicket = new BookingTicket();
        bookingTicket.setPassenger(passengers);
        bookingTicketService.createAuto(bookingTicket);
        model.addAttribute("booking",bookingTicket);
        return "information_ticket/information_ticket";
    }
}
