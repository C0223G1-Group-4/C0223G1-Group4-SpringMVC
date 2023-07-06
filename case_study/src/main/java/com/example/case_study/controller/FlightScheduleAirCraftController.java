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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("flight-schedule-air-craft")
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
    public String getList(@PageableDefault(value = 6) Pageable pageable, Model model) {
        model.addAttribute("flightScheduleAirCraft", iFlightScheduleAirCraftService.getAllList(pageable));
        return "flight-schedule-air-craft/view";
    }
    // Tài
    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("flightScheduleAirCraft", new FlightScheduleAirCraft());
        model.addAttribute("airCraftList", iAirCraftService.checkAllListAirCraft());
        model.addAttribute("routeList", iRouteService.checkAllListRoute());
        model.addAttribute("flightScheduleList", iFlightScheduleService.checkAllListFlightSchedule());
        return "flight-schedule-air-craft/create";
    }
    // Tài
    @PostMapping("create")
    public String create(@ModelAttribute FlightScheduleAirCraft flightScheduleAirCraft, @RequestParam int idRoute, RedirectAttributes redirectAttributes) {
            int count = 0;
        LocalDate localDate = LocalDate.parse((flightScheduleAirCraft.getFlightSchedule().getDeparture()).substring(0,10));
        int dateCheck = localDate.getDayOfYear();
        List<FlightScheduleAirCraft> flightScheduleAirCraftList = this.iFlightScheduleAirCraftService.checkAllListFlightScheduleAirCraft();
        for (FlightScheduleAirCraft f : flightScheduleAirCraftList) {
            LocalDate localDateLoop = LocalDate.parse(f.getFlightSchedule().getDeparture().substring(0,10));
            int dateLoop = localDateLoop.getDayOfYear();
            if (f.getFlightSchedule().getId() == flightScheduleAirCraft.getFlightSchedule().getId()
                    && f.getIdAirCraft().getRoutes().equals(flightScheduleAirCraft.getIdAirCraft().getRoutes())
                    && f.getIdAirCraft().equals(flightScheduleAirCraft.getIdAirCraft())
                    && dateCheck == dateLoop) {
                count++;
            }
        }
        if (count != 0) {
            redirectAttributes.addFlashAttribute("msg", "Đã có lịch bay này không thể thêm mới");
        } else {
            List<Route> routeList = new ArrayList<>();
            routeList.add(this.iRouteService.findByIdRoute(idRoute));
            this.iFlightScheduleAirCraftService.createFlightScheduleAirCraft(flightScheduleAirCraft);
            this.iAirCraftService.findByIdAirCraft(flightScheduleAirCraft.getIdAirCraft().getId()).setRoutes(routeList);
            this.iAirCraftService.editAirCraft(this.iAirCraftService.findByIdAirCraft(flightScheduleAirCraft.getIdAirCraft().getId()));
            redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        }
        return "redirect:/flight-schedule-air-craft";
    }
    // Tài
    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(id) != null) {
            model.addAttribute("flightScheduleAirCraft", this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(id));
            model.addAttribute("route",this.iRouteService.findByIdRoute(this.iFlightScheduleAirCraftService.findByIdFlightScheduleAirCraft(id).getIdAirCraft().getRoutes().get(0).getId()));
            model.addAttribute("airCraftList", iAirCraftService.checkAllListAirCraft());
            model.addAttribute("routeList", iRouteService.checkAllListRoute());
            model.addAttribute("flightScheduleList", iFlightScheduleService.checkAllListFlightSchedule());
            return "flight-schedule-air-craft/edit";
        }
        redirectAttributes.addFlashAttribute("msg", "Không tìm thấy đối tượng này");
        return "redirect:/flight-schedule";
    }
    // Tài
@PostMapping("edit")
    public String edit(@ModelAttribute FlightScheduleAirCraft flightScheduleAirCraft,@RequestParam int idRoute,Model model,RedirectAttributes redirectAttributes){
    int count = 0;
    LocalDate localDate = LocalDate.parse((flightScheduleAirCraft.getFlightSchedule().getDeparture()).substring(0,10));
    int dateCheck = localDate.getDayOfYear();
    List<FlightScheduleAirCraft> flightScheduleAirCraftList = this.iFlightScheduleAirCraftService.checkAllListFlightScheduleAirCraft();
    for (FlightScheduleAirCraft f : flightScheduleAirCraftList) {
        LocalDate localDateLoop = LocalDate.parse(f.getFlightSchedule().getDeparture().substring(0,10));
        int dateLoop = localDateLoop.getDayOfYear();
        if (f.getFlightSchedule().getId() == flightScheduleAirCraft.getFlightSchedule().getId()
                && f.getIdAirCraft().getRoutes().equals(flightScheduleAirCraft.getIdAirCraft().getRoutes())
                && f.getIdAirCraft().equals(flightScheduleAirCraft.getIdAirCraft())
                && dateCheck == dateLoop
                &&!(f.getId().equals(flightScheduleAirCraft.getId()))){
            count++;
        }
    }
    if (count != 0) {
        redirectAttributes.addFlashAttribute("msg", "Lịch bay này đã có không thể sửa");
    } else {
        List<Route> routeList = new ArrayList<>();
        routeList.add(this.iRouteService.findByIdRoute(idRoute));
        this.iFlightScheduleAirCraftService.editFlightScheduleAirCraft(flightScheduleAirCraft);
        this.iAirCraftService.findByIdAirCraft(flightScheduleAirCraft.getIdAirCraft().getId()).setRoutes(routeList);
        this.iAirCraftService.editAirCraft(this.iAirCraftService.findByIdAirCraft(flightScheduleAirCraft.getIdAirCraft().getId()));
        redirectAttributes.addFlashAttribute("msg", "Sửa mới thành công");
    }
    return "redirect:/flight-schedule-air-craft";
}
}
