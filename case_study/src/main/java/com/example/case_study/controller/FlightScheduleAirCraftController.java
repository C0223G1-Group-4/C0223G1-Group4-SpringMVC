package com.example.case_study.controller;

import com.example.case_study.dto.FlightScheduleDto;
import com.example.case_study.model.tai.FlightSchedule;
import com.example.case_study.model.tai.FlightScheduleAirCraft;
import com.example.case_study.model.tai.Route;
import com.example.case_study.service.aircraft_service.IAirCraftService;
import com.example.case_study.service.flight_scgedule_air_craft_service.IFlightScheduleAirCraftService;
import com.example.case_study.service.flight_schedule_service.IFlightScheduleService;
import com.example.case_study.service.route_service.IRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/flight-schedule-air-craft")
public class FlightScheduleAirCraftController {
    @Autowired
    private IFlightScheduleAirCraftService iFlightScheduleAirCraftService;
    @Autowired
    private IAirCraftService iAirCraftService;
    @Autowired
    private IRouteService iRouteService;
    @Autowired
    private IFlightScheduleService iFlightScheduleService;

    // Tài
    @GetMapping("")
    public String getList(@PageableDefault(value = 4) Pageable pageable, Model model) throws ParseException {
        Map<Integer, Date> dateMapDeparture = new HashMap<>();
        Map<Integer, Date> dateMapArrival = new HashMap<>();
        if (iFlightScheduleAirCraftService.checkAllListFlightScheduleAirCraft().size() != 0) {
            for (FlightScheduleAirCraft f : iFlightScheduleAirCraftService.checkAllListFlightScheduleAirCraft()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = format.parse(f.getFlightSchedule().getDeparture());
                Date date1 = format.parse(f.getFlightSchedule().getArrival());
                dateMapDeparture.put(f.getId(), date);
                dateMapArrival.put(f.getId(), date1);
            }
        }
        model.addAttribute("dateMapDeparture", dateMapDeparture);
        model.addAttribute("dateMapArrival", dateMapArrival);
        model.addAttribute("flightScheduleAirCraft", iFlightScheduleAirCraftService.getAllList(pageable));
        return "flight-schedule-air-craft/view";
    }

    // Tài
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("flightScheduleAirCraft", new FlightScheduleAirCraft());
        model.addAttribute("airCraftList", iAirCraftService.checkAllListAirCraft());
        model.addAttribute("routeList", iRouteService.checkAllListRoute());
        model.addAttribute("flightScheduleList", iFlightScheduleService.checkAllListFlightSchedule());
        return "flight-schedule-air-craft/create";
    }

    // Tài
    @PostMapping("/create")
    public String create(@ModelAttribute FlightScheduleAirCraft flightScheduleAirCraft, RedirectAttributes redirectAttributes) {
        int count = 0;
        LocalDate localDate = LocalDate.parse((flightScheduleAirCraft.getFlightSchedule().getDeparture()).substring(0, 10));
        int dateCheck = localDate.getDayOfYear();
        List<FlightScheduleAirCraft> flightScheduleAirCraftList = this.iFlightScheduleAirCraftService.checkAllListFlightScheduleAirCraft();
        for (FlightScheduleAirCraft f : flightScheduleAirCraftList) {
            LocalDate localDateLoop = LocalDate.parse(f.getFlightSchedule().getDeparture().substring(0, 10));
            int dateLoop = localDateLoop.getDayOfYear();
            if (  f.getIdAirCraft().equals(flightScheduleAirCraft.getIdAirCraft())
                    && dateCheck == dateLoop
                    && flightScheduleAirCraft.getFlightSchedule().getArrival().substring(11, 16).equals(f.getFlightSchedule().getArrival().substring(11, 16))
                    && flightScheduleAirCraft.getFlightSchedule().getDeparture().substring(11, 16).equals(f.getFlightSchedule().getDeparture().substring(11, 16))
                    )
             {
                count++;
            }
        }
        if (count != 0) {
            redirectAttributes.addFlashAttribute("msgErr", "Have in list can't create");
            return "redirect:/flight-schedule-air-craft";
        } else {
            this.iFlightScheduleAirCraftService.createFlightScheduleAirCraft(flightScheduleAirCraft);
            redirectAttributes.addFlashAttribute("msg", "Create success");
        }
        return "redirect:/flight-schedule-air-craft";
    }

    // Tài
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(id) != null) {
            model.addAttribute("flightScheduleAirCraft", this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(id));
            model.addAttribute("route", this.iRouteService.findByIdRoute(this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(id).getIdAirCraft().getRoutes().get(0).getId()));
            model.addAttribute("airCraftList", iAirCraftService.checkAllListAirCraft());
            model.addAttribute("routeList", iRouteService.checkAllListRoute());
            model.addAttribute("flightScheduleList", iFlightScheduleService.checkAllListFlightSchedule());
            return "flight-schedule-air-craft/edit";
        }
        redirectAttributes.addFlashAttribute("msgErr", "Not found");
        return "redirect:/flight-schedule";
    }

    // Tài
    @PostMapping("/edit")
    public String edit(@ModelAttribute FlightScheduleAirCraft flightScheduleAirCraft, @RequestParam int idRoute, Model model, RedirectAttributes redirectAttributes) {
        int count = 0;
        LocalDate localDate = LocalDate.parse((flightScheduleAirCraft.getFlightSchedule().getDeparture()).substring(0, 10));
        int dateCheck = localDate.getDayOfYear();
        List<FlightScheduleAirCraft> flightScheduleAirCraftList = this.iFlightScheduleAirCraftService.checkAllListFlightScheduleAirCraft();
        for (FlightScheduleAirCraft f : flightScheduleAirCraftList) {
            LocalDate localDateLoop = LocalDate.parse(f.getFlightSchedule().getDeparture().substring(0, 10));
            int dateLoop = localDateLoop.getDayOfYear();
            if (f.getFlightSchedule().getId() == flightScheduleAirCraft.getFlightSchedule().getId()
                    && f.getIdAirCraft().getRoutes().equals(flightScheduleAirCraft.getIdAirCraft().getRoutes())
                    && f.getIdAirCraft().equals(flightScheduleAirCraft.getIdAirCraft())
                    && dateCheck == dateLoop
                    && flightScheduleAirCraft.getFlightSchedule().getArrival().substring(11, 16).equals(f.getFlightSchedule().getArrival().substring(11, 16))
                    && flightScheduleAirCraft.getFlightSchedule().getDeparture().substring(11, 16).equals(f.getFlightSchedule().getDeparture().substring(11, 16))
                    && !(f.getId().equals(flightScheduleAirCraft.getId()))
                    &&flightScheduleAirCraft.getIdAirCraft().getRoutes().get(0).getId()!=f.getIdAirCraft().getRoutes().get(0).getId()
            ) {
                count++;
            }
        }
        if (count != 0) {
            redirectAttributes.addFlashAttribute("msgErr", "Already exists");
        } else {
            List<Route> routeList = new ArrayList<>();
            routeList.add(this.iRouteService.findByIdRoute(idRoute));
            this.iFlightScheduleAirCraftService.editFlightScheduleAirCraft(flightScheduleAirCraft);
            this.iAirCraftService.findByIdAirCraft(flightScheduleAirCraft.getIdAirCraft().getId()).setRoutes(routeList);
            this.iAirCraftService.editAirCraft(this.iAirCraftService.findByIdAirCraft(flightScheduleAirCraft.getIdAirCraft().getId()));
            redirectAttributes.addFlashAttribute("msg", "Edit Success");
        }

        return "redirect:/flight-schedule-air-craft";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int deleteId, RedirectAttributes redirectAttributes) {
        if (this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(deleteId) != null) {
            this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(deleteId).setFlag(true);
            this.iFlightScheduleAirCraftService.deleteFlightScheduleAirCraft(this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(deleteId));
            redirectAttributes.addFlashAttribute("msg", "Delete Success");
        } else {
            redirectAttributes.addFlashAttribute("msgErr", "Delete Fail");
        }
        return "redirect:/flight-schedule-air-craft";
    }


}
