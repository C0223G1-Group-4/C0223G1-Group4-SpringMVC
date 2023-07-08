package com.example.case_study.controller;

import com.example.case_study.dto.FlightScheduleDto;
import com.example.case_study.model.tai.FlightSchedule;
import com.example.case_study.model.tai.FlightScheduleAirCraft;
import com.example.case_study.model.tai.Route;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/flight-schedule")
public class FlightScheduleController {
    @Autowired
    private IFlightScheduleService iFlightScheduleService;
    // Tài
    @GetMapping("")
    public String getList(@PageableDefault(value = 4) Pageable pageable, Model model) throws ParseException {
        Map<Integer,Date> dateMapDeparture=new HashMap<>();
        Map<Integer,Date> dateMapArrival=new HashMap<>();
        if (iFlightScheduleService.checkAllListFlightSchedule().size()!=0){
        for (FlightSchedule f: iFlightScheduleService.checkAllListFlightSchedule()) {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date date=format.parse(f.getDeparture());
                Date date1=format.parse(f.getArrival());
                dateMapDeparture.put(f.getId(),date);
                dateMapArrival.put(f.getId(),date1);
            }
        }
        model.addAttribute("dateMapDeparture",dateMapDeparture);
        model.addAttribute("dateMapArrival",dateMapArrival);
        model.addAttribute("flightSchedule", iFlightScheduleService.getAllListFlightSchedule(pageable));
        return "flight-schedule/view";
    }
    // Tài
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("flightScheduleDto", new FlightScheduleDto());
        return "flight-schedule/create";
    }
    // Tài
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute FlightScheduleDto flightScheduleDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "flight-schedule/create";
        }
        FlightSchedule flightSchedule = new FlightSchedule();
        BeanUtils.copyProperties(flightScheduleDto, flightSchedule);
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTimeDeparture = LocalDateTime.parse(flightSchedule.getDeparture().substring(0, 10).concat(" " + flightSchedule.getDeparture().substring(11, 16)), formatter);
        LocalDateTime localDateTimeArrival = LocalDateTime.parse(flightSchedule.getArrival().substring(0, 10).concat(" " + flightSchedule.getArrival().substring(11, 16)), formatter);
        int dayDeparture = localDateTimeDeparture.getDayOfYear();
        int hourDeparture = localDateTimeDeparture.getHour();
        int minuteDeparture = localDateTimeDeparture.getMinute();
        int dayArrival = localDateTimeArrival.getDayOfYear();
        int hourArrival = localDateTimeArrival.getHour();
        int minuteArrival = localDateTimeArrival.getMinute();
        for (FlightSchedule f : this.iFlightScheduleService.checkAllListFlightSchedule()) {
            LocalDateTime localDateTimeLoopDeparture = LocalDateTime.parse(f.getDeparture().substring(0, 10).concat(" " + f.getDeparture().substring(11, 16)), formatter);
            LocalDateTime localDateTimeLoopArrival = LocalDateTime.parse(f.getArrival().substring(0, 10).concat(" " + f.getArrival().substring(11, 16)), formatter);
            int dayLoopDeparture = localDateTimeLoopDeparture.getDayOfYear();
            int hourLoopDeparture = localDateTimeLoopDeparture.getHour();
            int minuteLoopDeparture = localDateTimeLoopDeparture.getMinute();
            int dayLoopArrival = localDateTimeLoopArrival.getDayOfYear();
            int hourLoopArrival = localDateTimeLoopArrival.getHour();
            int minuteLoopArrival = localDateTimeLoopArrival.getMinute();
            if (dayLoopDeparture == dayDeparture
                    && dayArrival == dayLoopArrival
                    && hourDeparture == hourLoopDeparture
                    &&hourArrival==hourLoopArrival
                    &&minuteDeparture==minuteLoopDeparture
                    &&minuteArrival==minuteLoopArrival){
                count++;
            }
        }
        if (count==0){
            int codeFlightSchedule=0;
            if (this.iFlightScheduleService.checkAllListFlightSchedule().size()==0){
                codeFlightSchedule=1;
            }else {
             codeFlightSchedule =this.iFlightScheduleService.checkAllListFlightSchedule().get(this.iFlightScheduleService.checkAllListFlightSchedule().size()-1).getId()+1;
            }

            flightSchedule.setCodeFlightSchedule("FS-"+codeFlightSchedule);
            if (this.iFlightScheduleService.createFlightSchedule(flightSchedule)) {
                redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Không thể thêm mới đối tượng này");
            }
        }else {
            redirectAttributes.addFlashAttribute("msg", "Đã có lịch này trong danh sách");
        }

        return "redirect:/flight-schedule";
    }
    // Tài
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (this.iFlightScheduleService.findByIdFlightSchedule(id) != null) {
            model.addAttribute("flightSchedule", this.iFlightScheduleService.findByIdFlightSchedule(id));
            return "flight-schedule/edit";
        }
        redirectAttributes.addFlashAttribute("msg", "Không tìm thấy đối tượng này");
        return "redirect:/flight-schedule";
    }
    // Tài
    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute FlightScheduleDto flightScheduleDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "flight-schedule/view";
        }
        FlightSchedule flightSchedule = new FlightSchedule();
        BeanUtils.copyProperties(flightScheduleDto, flightSchedule);
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTimeDeparture = LocalDateTime.parse(flightSchedule.getDeparture().substring(0, 10).concat(" " + flightSchedule.getDeparture().substring(11, 16)), formatter);
        LocalDateTime localDateTimeArrival = LocalDateTime.parse(flightSchedule.getArrival().substring(0, 10).concat(" " +flightSchedule.getArrival().substring(11, 16)), formatter);
        int dayDeparture = localDateTimeDeparture.getDayOfYear();
        int hourDeparture = localDateTimeDeparture.getHour();
        int minuteDeparture = localDateTimeDeparture.getMinute();
        int dayArrival = localDateTimeArrival.getDayOfYear();
        int hourArrival = localDateTimeArrival.getHour();
        int minuteArrival = localDateTimeArrival.getMinute();
        for (FlightSchedule f : this.iFlightScheduleService.checkAllListFlightSchedule()) {
            LocalDateTime localDateTimeLoopDeparture = LocalDateTime.parse(f.getDeparture().substring(0, 10).concat(" " + f.getDeparture().substring(11, 16)), formatter);
            LocalDateTime localDateTimeLoopArrival = LocalDateTime.parse(f.getArrival().substring(0, 10).concat(" " + f.getArrival().substring(11, 16)), formatter);
            int dayLoopDeparture = localDateTimeLoopDeparture.getDayOfYear();
            int hourLoopDeparture = localDateTimeLoopDeparture.getHour();
            int minuteLoopDeparture = localDateTimeLoopDeparture.getMinute();
            int dayLoopArrival = localDateTimeLoopArrival.getDayOfYear();
            int hourLoopArrival = localDateTimeLoopArrival.getHour();
            int minuteLoopArrival = localDateTimeLoopArrival.getMinute();
            if (dayLoopDeparture == dayDeparture
                    && dayArrival == dayLoopArrival
                    && hourDeparture == hourLoopDeparture
                    &&hourArrival==hourLoopArrival
                    &&minuteDeparture==minuteLoopDeparture
                    &&minuteArrival==minuteLoopArrival
            &&!(flightSchedule.getId().equals(f.getId()))) {
                count++;
            }
        }
        if (count==0){
            if (this.iFlightScheduleService.editFlightSchedule(flightSchedule)) {
                redirectAttributes.addFlashAttribute("msg", "Sửa đối tượng thành công");
            } else {
                redirectAttributes.addFlashAttribute("msgErr", "Không tìm thấy đối tượng này");
            }
        }else {
            redirectAttributes.addFlashAttribute("msgErr", "Lịch này đã có trong danh sách không thể sửa");
        }

        return "redirect:/flight-schedule";
    }
    // Tài
    @GetMapping("/delete")
    public String delete(@RequestParam int deleteId, RedirectAttributes redirectAttributes) {
        if (this.iFlightScheduleService.findByIdFlightSchedule(deleteId) != null) {
            this.iFlightScheduleService.findByIdFlightSchedule(deleteId).setFlag(true);
            this.iFlightScheduleService.deleteFlightSchedule(this.iFlightScheduleService.findByIdFlightSchedule(deleteId));
            redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Không tìm thấy đối tượng này");
        }
        return "redirect:/flight-schedule";
    }
}
