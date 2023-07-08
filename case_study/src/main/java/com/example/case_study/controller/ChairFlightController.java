package com.example.case_study.controller;

import com.example.case_study.model.ChairFlight;
import com.example.case_study.model.tai.FlightScheduleAirCraft;
import com.example.case_study.service.chairflight_service.IChairFlightService;
import com.example.case_study.service.flight_scgedule_air_craft_service.IFlightScheduleAirCraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/chair_flight")
public class ChairFlightController {
    @Autowired
    private IChairFlightService chairFlightService;
    @Autowired
    private IFlightScheduleAirCraftService flightScheduleAirCraftService;

    @GetMapping("")
    public String getList(Model model) {
        int idCraftSchedule = 2; //cần thay lại khi truyền qua
        List<ChairFlight> list = chairFlightService.getList(idCraftSchedule);
        model.addAttribute("idCraftSchedule", idCraftSchedule);
        model.addAttribute("list", list);
        return "chair_flight/chair_flight";
    }

    @PostMapping("/create")
    public String create(@RequestParam("chair") List<ChairFlight> chairFlightList, @RequestParam int idCraftSchedule,
                         RedirectAttributes redirectAttributes) {
        int quantity = chairFlightList.size();
//        for (ChairFlight c: chairFlightList
//             ) {
//            c.setStatusChair(true);
//            this.chairFlightService.update(c);
//        }
        FlightScheduleAirCraft flightScheduleAirCraft =
                flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule);
        redirectAttributes.addFlashAttribute("quantity", quantity);
        redirectAttributes.addAttribute("flightScheduleAirCraft", flightScheduleAirCraft);
        return "redirect:/ticket";
    }
}
