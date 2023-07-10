package com.example.case_study.controller;

import com.example.case_study.model.ChairFlight;
import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.FlightSchedule;
import com.example.case_study.model.tai.FlightScheduleAirCraft;
import com.example.case_study.service.aircraft_service.IAirCraftService;
import com.example.case_study.service.chairflight_service.IChairFlightService;
import com.example.case_study.service.flight_scgedule_air_craft_service.IFlightScheduleAirCraftService;
import com.example.case_study.service.flight_schedule_service.IFlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/chair_flight")
public class ChairFlightController {
    @Autowired
    private IChairFlightService chairFlightService;
    @Autowired
    private IFlightScheduleAirCraftService flightScheduleAirCraftService;

    @Autowired
    private IAirCraftService airCraftService;

    @Autowired
    private IFlightScheduleService flightScheduleService;
    @GetMapping("{id}")
    public String getList(Model model, @PathVariable int id) {
        int idCraftSchedule = id; //cần thay lại khi truyền qua
        List<ChairFlight> list = chairFlightService.getList(idCraftSchedule);
        model.addAttribute("idCraftSchedule", idCraftSchedule);
        model.addAttribute("list", list);
        return "chair_flight/chair_flight";
    }

    @PostMapping("/create")
    public String create(@RequestParam("chair") List<ChairFlight> chairFlightList, @RequestParam int idCraftSchedule,
                         RedirectAttributes redirectAttributes, @SessionAttribute List<ChairFlight> listChair) {
        int quantity = chairFlightList.size();
        listChair.addAll(chairFlightList);
//        for (ChairFlight c: chairFlightList
//             ) {
//            c.setStatusChair(true);
//            this.chairFlightService.update(c);
//        }
        int idSchedule =
                flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule).getFlightSchedule().getId();
        int idAirCraft = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule).getIdAirCraft().getId();
        String name= airCraftService.findByIdAirCraft(idAirCraft).getNumberAirCraft();
        String flightSchedule= flightScheduleService.findByIdFlightSchedule(idSchedule).getArrival();
        String departureDate = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule).
                getFlightSchedule().getDeparture();
        String arrivalDate = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule).
                getFlightSchedule().getArrival();
        String airlineCode = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule).
                getIdAirCraft().getNumberAirCraft();
        String from = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule)
                .getIdAirCraft().getRoutes().get(0).getAirPort();
        String to = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule)
                .getIdAirCraft().getRoutes().get(0).getDestination();
        double price = flightScheduleAirCraftService.findByIdFlightScheduleAirCraft(idCraftSchedule)
                .getIdAirCraft().getRoutes().get(0).getFare();


        redirectAttributes.addFlashAttribute("quantity", quantity);
        redirectAttributes.addFlashAttribute("name",name);
        redirectAttributes.addFlashAttribute("flightSchedule",flightSchedule);
        redirectAttributes.addFlashAttribute("departureDate",departureDate);
        redirectAttributes.addFlashAttribute("arrivalDate",arrivalDate);
        redirectAttributes.addFlashAttribute("airlineCode",airlineCode);
        redirectAttributes.addFlashAttribute("from",from);
        redirectAttributes.addFlashAttribute("to",to);
        redirectAttributes.addFlashAttribute("price",price);
        return "redirect:/ticket";
    }
}
