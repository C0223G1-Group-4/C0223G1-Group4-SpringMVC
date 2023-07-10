package com.example.case_study.controller;

import com.example.case_study.service.receive_booking_service.IReceiveBookingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/receive_booking")
public class ReceiveBookingTicketController {
    @Autowired
    private IReceiveBookingService receiveBookingService;

    @GetMapping("/receive")
    public String receive(@PageableDefault(value = 6) Pageable pageable, Model model){
//        receiveBookingService.cancelBookingStatus();
        model.addAttribute("list",receiveBookingService.getReceiveBookingTicketList(pageable));
        return "receive_booking/booking_list";
    }

    @GetMapping("/cancelStatus/{id}")
    public String cancelStatus(@PathVariable int id, RedirectAttributes redirectAttributes){
        if(receiveBookingService.findById(id)!=null) {
            receiveBookingService.cancelBooking(id);
            redirectAttributes.addFlashAttribute("success", "Successfully canceled ticket");
        }else{
            redirectAttributes.addFlashAttribute("fail", "This ticket code could not be found.");
        }
        return "receive_booking/booking_list";
    }
}
