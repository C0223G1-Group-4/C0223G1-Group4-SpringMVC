package com.example.case_study.controller;

import com.example.case_study.dto.ReceiveBookingDto;
import com.example.case_study.service.receive_booking_service.IReceiveBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private IReceiveBookingService receiveBookingService;
    @GetMapping("{email}")
    public String history(@PathVariable String email, @PageableDefault(value = 4) Pageable pageable, Model model) {
        Page<ReceiveBookingDto> histories=receiveBookingService.getHistory(email,pageable);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDate ;
        LocalDateTime cancel = LocalDateTime.now().plusDays(1);
        for (ReceiveBookingDto rc : histories) {
            parsedDate = LocalDateTime.parse(rc.getDeparture().substring(0, 10).concat(" " + rc.getDeparture().substring(11, 16)), formatter);
            if (cancel.isBefore(parsedDate)) {
                rc.setStatus_receive(true);
                System.out.println(rc);
            } else {
                rc.setStatus_receive(false);
            }
        }
        model.addAttribute("list", histories);
        return "history/history";
    }

    @GetMapping("/cancelStatus")
    public String cancelStatus(@RequestParam int cancel1, RedirectAttributes redirectAttributes) {
        if (receiveBookingService.findById(cancel1) != null) {
            receiveBookingService.cancelBooking(cancel1);
            redirectAttributes.addFlashAttribute("success", "Successfully canceled ticket");
        } else {
            redirectAttributes.addFlashAttribute("fail", "This ticket code could not be found.");
        }
        return "redirect:/history";
    }
}
