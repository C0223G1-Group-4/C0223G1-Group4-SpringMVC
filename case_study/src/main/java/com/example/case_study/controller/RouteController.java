package com.example.case_study.controller;

import com.example.case_study.dto.RouteDto;
import com.example.case_study.model.tai.Route;
import com.example.case_study.service.aircraft_service.IAirCraftService;
import com.example.case_study.service.route_service.IRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private IRouteService iRouteService;
    @Autowired
    private IAirCraftService iAirCraftService;
    // Tài
    @GetMapping("")
    public String getListRoute(@PageableDefault(value = 4) Pageable pageable,Model model ){
        model.addAttribute("listRoute",iRouteService.getAllListRoute(pageable));
        return "route/view";
    }
    // Tài
    @GetMapping("/create")
    public String create(Model model){
        Route route=new Route();
        route.setAirPort("Đà Nẵng");
        model.addAttribute("routeDto",route);
        model.addAttribute("listAirCraft",this.iAirCraftService.checkAllListAirCraft());
        return "route/create";
    }
    // Tài
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute RouteDto routeDto, BindingResult bindingResult,@RequestParam String destination, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "route/create";
        }
        Route route=new Route();
        BeanUtils.copyProperties(routeDto,route);
        int codeRoute=0;

        if (this.iRouteService.checkAllListRoute().size()==0){
            codeRoute=1;
        }else {
            codeRoute= this.iRouteService.checkAllListRoute().get(this.iRouteService.checkAllListRoute().size()-1).getId()+1;
        }
        route.setDestination(destination);
        route.setCodeRoute("CR-"+codeRoute);
        if (this.iRouteService.createRoute(route)){
            redirectAttributes.addFlashAttribute("msg","Create success");
        }else {
            redirectAttributes.addFlashAttribute("msg","Already exists");
        }
        return "redirect:/route";
    }
    // Tài
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model,RedirectAttributes redirectAttributes){
          if (this.iRouteService.findByIdRoute(id)!=null){
              model.addAttribute("number",this.iRouteService.findByIdRoute(id).getCodeRoute());
              model.addAttribute("route",this.iRouteService.findByIdRoute(id));
              return "route/edit";
          }else {
              redirectAttributes.addFlashAttribute("msg","Not found");
              return "redirect:/route";
          }
    }
    // Tài
    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute RouteDto routeDto,BindingResult bindingResult,@RequestParam String number,@RequestParam int id, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "route/edit";
        }
        Route route=new Route();
        BeanUtils.copyProperties(routeDto,route);
        for (Route r: this.iRouteService.checkAllListRoute()) {
            if (r.getCodeRoute().equals(route.getCodeRoute())&&!r.getId().equals(id)&&!route.getCodeRoute().equals(number)){
                redirectAttributes.addFlashAttribute("msgErr","Can't edit");
                return "redirect:/route";
            }
        }
        if (this.iRouteService.editRoute(route)){
            redirectAttributes.addFlashAttribute("msg","Edit success");
        }else {
            redirectAttributes.addFlashAttribute("msgErr","Not found");
        }
        return "redirect:/route";
    }
    // Tài
    @GetMapping("/delete")
    public String delete(@RequestParam int deleteId,RedirectAttributes redirectAttributes){
        if (this.iRouteService.findByIdRoute(deleteId)!=null){
            this.iRouteService.findByIdRoute(deleteId).setFlag(true);
            this.iRouteService.deleteRoute(this.iRouteService.findByIdRoute(deleteId));
            redirectAttributes.addFlashAttribute("msg","Delete success");
        }else {
            redirectAttributes.addFlashAttribute("msgErr","Not found");
        }
        return "redirect:/route";
    }
}
