package com.example.case_study.controller;

import com.example.case_study.model.tai.FlightScheduleAirCraft;
import com.example.case_study.service.flight_scgedule_air_craft_service.IFlightScheduleAirCraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired
    private IFlightScheduleAirCraftService iFlightScheduleAirCraftService;

    @GetMapping("")
    public String search(@RequestParam(defaultValue = "",required = false) String airPort,@RequestParam(defaultValue = "", required = false) String departure, @RequestParam(defaultValue = "", required = false) String arrival, @RequestParam(defaultValue = "",required = false) String destination, Model model, RedirectAttributes redirectAttributes) throws ParseException {
        List<FlightScheduleAirCraft> flightScheduleAirCrafts = this.iFlightScheduleAirCraftService.searchTicket(airPort,departure, arrival, destination);
        if (flightScheduleAirCrafts.size() == 0) {
            model.addAttribute("msgErr", "Not found ticket");
        }
        model.addAttribute("listTicket", flightScheduleAirCrafts);
        return "flight-schedule-air-craft/search";
    }
}
