package com.example.case_study.controller;

import com.example.case_study.dto.AirCraftDto;
import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.Route;
import com.example.case_study.service.aircraft_service.IAirCraftService;
import com.example.case_study.service.route_service.IRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/air-craft")
public class AirCraftController {
    @Autowired
    private IAirCraftService iAirCraftService;
    @Autowired
    private IRouteService iRouteService;
    // Tài
    @GetMapping("")
    public String getList(@PageableDefault(value = 4) Pageable pageable ,Model model ) {

        Page<AirCraft> airCraftPage=iAirCraftService.getAllListAirCraft(pageable);
        model.addAttribute("aircraftList", airCraftPage);
        return "air-craft/view";
    }
    // Tài
    @GetMapping("/create")
    public String createAirCraft(Model model) {
        model.addAttribute("airCraftDto", new AirCraftDto());
        model.addAttribute("routeList",this.iRouteService.checkAllListRoute());
        return "air-craft/create";
    }
    // Tài
    @PostMapping("/create")
    public String createAirCraft(@ModelAttribute AirCraft airCraft, RedirectAttributes redirectAttributes) {
       if (this.iAirCraftService.checkAllListAirCraft().size()==0){
           airCraft.setNumberAirCraft("AC-"+1);
           airCraft.setCapacity(60);
       }else {
           int numberAirCraft=this.iAirCraftService.checkAllListAirCraft().get(this.iAirCraftService.checkAllListAirCraft().size()-1).getId()+1;
           airCraft.setNumberAirCraft("AC-"+(numberAirCraft));
           airCraft.setCapacity(60);
       }
        this.iAirCraftService.createAirCraft(airCraft);
        redirectAttributes.addFlashAttribute("msg", "Create success");
        return "redirect:/air-craft";
    }
    // Tài
    @GetMapping("/edit/{id}")
    public String editAirCraft(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (!this.iAirCraftService.findByIdAirCraft(id).isFlag()&& this.iAirCraftService.findByIdAirCraft(id)!= null) {
            model.addAttribute("number",this.iAirCraftService.findByIdAirCraft(id).getNumberAirCraft());
            model.addAttribute("airCraft", this.iAirCraftService.findByIdAirCraft(id));
            return "air-craft/edit";
        } else {
            redirectAttributes.addAttribute("msg", "Not found");
            return "redirect:/air-craft";
        }
    }
    // Tài
    @PostMapping("/edit")
    public String editAirCraft(@Valid @ModelAttribute AirCraftDto airCraftDto, BindingResult bindingResult,@RequestParam String number,@RequestParam int id, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "air-craft/edit";
        }

            AirCraft airCraft = new AirCraft();
            BeanUtils.copyProperties(airCraftDto, airCraft);
        for (AirCraft a: this.iAirCraftService.checkAllListAirCraft()) {
            if (airCraft.getNumberAirCraft().equals(a.getNumberAirCraft())&&!a.getId().equals(id)){
                redirectAttributes.addFlashAttribute("msgErr","Number AirCraft have in list can't edit");
                return "redirect:/air-craft";
            }
        }
            this.iAirCraftService.editAirCraft(airCraft);
            redirectAttributes.addFlashAttribute("msg","Edit Success");
        return "redirect:/air-craft";
    }
    // Tài
    @GetMapping("/delete")
    public String deleteAirCraft(@RequestParam int deleteId,RedirectAttributes redirectAttributes){
            if (this.iAirCraftService.findByIdAirCraft(deleteId)!=null){
                this.iAirCraftService.findByIdAirCraft(deleteId).setFlag(true);
                this.iAirCraftService.deleteAirCraft(this.iAirCraftService.findByIdAirCraft(deleteId));
                redirectAttributes.addFlashAttribute("msg","Delete success");
            }else {
                redirectAttributes.addFlashAttribute("msg","Not found");
            }

        return "redirect:/air-craft";
    }
}
