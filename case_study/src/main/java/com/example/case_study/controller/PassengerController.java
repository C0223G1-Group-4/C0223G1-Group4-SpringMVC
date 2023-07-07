package com.example.case_study.controller;

import com.example.case_study.dto.EmployeeDto;
import com.example.case_study.dto.PassengerDto;
import com.example.case_study.model.Employees;
import com.example.case_study.model.Passengers;
import com.example.case_study.service.passengers_service.IPassengersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private IPassengersService iPassengersService;

    @GetMapping()
    public String showListPassenger(@PageableDefault(value = 5,sort = "id", direction = Sort.Direction.DESC)
                                        Pageable pageable, @RequestParam(value = "search",defaultValue = "")
                                        String search, Model model){
        model.addAttribute("passenger", iPassengersService.findByPassengers(search, pageable));
        model.addAttribute("search", search);
        return "/list_passenger";
    }
//    @GetMapping("/create-form-passenger")
//    public String formCreatePassenger(Model model){
//        model.addAttribute("passengerDto", new PassengerDto());
//        return "/create_passenger";
//    }
//    @PostMapping("/add-passenger")
//    public String addPassenger(@Valid @ModelAttribute PassengerDto passengerDto, BindingResult bindingResult,
//                               RedirectAttributes redirectAttributes){
//        new PassengerDto().validate(passengerDto,bindingResult);
//        if (bindingResult.hasFieldErrors()){
//            return "/create_passenger";
//        }
//        Passengers passengers = new Passengers();
//        BeanUtils.copyProperties(passengerDto, passengers);
//        iPassengersService.create(passengers);
//        redirectAttributes.addFlashAttribute("msg1","Add passengerDto new success!");
//        return "redirect:/passenger";
//    }
    @GetMapping("/info/{id}")
    public String detailPassenger(@PathVariable Integer id, Model model){
        model.addAttribute("passenger",iPassengersService.findByIdPassengers(id));
        return "/detail_passenger";
    }
    @GetMapping("/edit/{id}")
    public String formUpdate(@PathVariable Integer id, Model model) {
        Passengers passengers = iPassengersService.findByIdPassengers(id);
        PassengerDto passengerDto = new PassengerDto();
        BeanUtils.copyProperties(passengers, passengerDto);
        model.addAttribute("passengerDto", passengerDto);
        return "/update_passenger";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Validated PassengerDto passengerDto,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasFieldErrors()) {
            return "/update_passenger";
        }
        Passengers passengers = new Passengers();
        BeanUtils.copyProperties(passengerDto, passengers);
        iPassengersService.update(passengers);
        redirectAttributes.addFlashAttribute("msg2", "Update passenger new success!");
        return "redirect:/passenger";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(value = "idDelete") Integer id, RedirectAttributes redirectAttributes) {
        iPassengersService.delete(id);
        redirectAttributes.addFlashAttribute("msg3","Delete employee new success!");
        return "redirect:/passenger";
    }
}
