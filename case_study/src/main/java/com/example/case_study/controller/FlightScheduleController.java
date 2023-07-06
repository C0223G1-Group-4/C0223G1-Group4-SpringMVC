package com.example.case_study.controller;

import com.example.case_study.dto.FlightScheduleDto;
import com.example.case_study.model.tai.FlightSchedule;
import com.example.case_study.repository.IFlightScheduleRepository;
import com.example.case_study.service.flight_schedule_service.IFlightScheduleService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("flight-schedule")
public class FlightScheduleController {
    @Autowired
    private IFlightScheduleService iFlightScheduleService;

    @GetMapping("")
    public String getList(@PageableDefault(value = 4)Pageable pageable, Model model ) {
        model.addAttribute("flightSchedule", iFlightScheduleService.getAllListFlightSchedule(pageable));
        return "flight-schedule/view";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("flightScheduleDto", new FlightScheduleDto());
        return "flight-schedule/create";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute FlightScheduleDto flightScheduleDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "flight-schedule/create";
        }
        FlightSchedule flightSchedule = new FlightSchedule();
        BeanUtils.copyProperties(flightScheduleDto, flightSchedule);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime=LocalDateTime.parse(flightSchedule.getDeparture());
        LocalDateTime localDateTime1=LocalDateTime.parse(flightSchedule.getDateFlightSchedule());
        LocalDateTime localDateTime2=LocalDateTime.parse(flightSchedule.getArrival());
        if (this.iFlightScheduleService.createFlightSchedule(flightSchedule)) {
            redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Không thể thêm mới đối tượng này");
        }
        return "redirect:/flight-schedule";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (this.iFlightScheduleService.findByIdFlightSchedule(id) != null) {
            model.addAttribute("flightSchedule", this.iFlightScheduleService.findByIdFlightSchedule(id));
            return "flight-schedule/edit";
        }
        redirectAttributes.addFlashAttribute("msg", "Không tìm thấy đối tượng này");
        return "redirect:/flight-schedule";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute FlightScheduleDto flightScheduleDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
              return "flight-schedule/view";
        }
        FlightSchedule flightSchedule=new FlightSchedule();
        BeanUtils.copyProperties(flightScheduleDto,flightSchedule);
        if (this.iFlightScheduleService.editFlightSchedule(flightSchedule)){
            redirectAttributes.addFlashAttribute("msg","Sửa đối tượng thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Không tìm thấy đối tượng này");
        }
        return "redirect:/flight-schedule";
    }
    @GetMapping("delete")
    public String delete(@RequestParam int deleteId,RedirectAttributes redirectAttributes){
        if (this.iFlightScheduleService.findByIdFlightSchedule(deleteId)!=null){
            this.iFlightScheduleService.findByIdFlightSchedule(deleteId).setFlag(false);
            this.iFlightScheduleService.deleteFlightSchedule(this.iFlightScheduleService.findByIdFlightSchedule(deleteId));
            redirectAttributes.addFlashAttribute("msg","Xóa thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Không tìm thấy đối tượng này");
        }
        return "redirect:/flight-schedule";
    }
}
