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
@RequestMapping("air-craft")
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
    @GetMapping("create")
    public String createAirCraft(Model model) {
        model.addAttribute("airCraftDto", new AirCraftDto());
        model.addAttribute("routeList",this.iRouteService.checkAllListRoute());
        return "air-craft/create";
    }
    // Tài
    @PostMapping("create")
    public String createAirCraft(@Valid @ModelAttribute AirCraftDto airCraftDto, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "air-craft/create";
        }
        AirCraft airCraft1 = new AirCraft();
        BeanUtils.copyProperties(airCraftDto, airCraft1);
//        List<Route> routes=new ArrayList<>();
//        routes.add(this.iRouteService.findByIdRoute(idRoute));
//        airCraft1.setRoutes(routes);
        this.iAirCraftService.createAirCraft(airCraft1);
        redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công");
        return "redirect:/aircraft";
    }
    // Tài
    @GetMapping("edit/{id}")
    public String editAirCraft(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (!this.iAirCraftService.findByIdAirCraft(id).isFlag()&& this.iAirCraftService.findByIdAirCraft(id)!= null) {
            model.addAttribute("airCraft", this.iAirCraftService.findByIdAirCraft(id));
            return "air-craft/edit";
        } else {
            redirectAttributes.addAttribute("msg", "Không tìm thấy đối tượng này");
            return "redirect:/aircraft";
        }
    }
    // Tài
    @PostMapping("edit")
    public String editAirCraft(@Valid @ModelAttribute AirCraftDto airCraftDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "air-craft/edit";
        }
        if (this.iAirCraftService.findByIdAirCraft(airCraftDto.getId())!=null){
            AirCraft airCraft = new AirCraft();
            BeanUtils.copyProperties(airCraftDto, airCraft);
            List<Route> routes=new ArrayList<>();
            routes.add(this.iRouteService.findByIdRoute(airCraft.getRoutes().get(0).getId()));
            airCraft.setRoutes(routes);
            this.iAirCraftService.editAirCraft(airCraft);
            redirectAttributes.addFlashAttribute("msg","Thay đổi thông tin máy bay thành công");
        }else {
            redirectAttributes.addFlashAttribute("msg","Không tìm thấy đối tượng này");
        }

        return "redirect:/aircraft";
    }
    // Tài
    @GetMapping("delete/{id}")
    public String deleteAirCraft(@PathVariable int id,RedirectAttributes redirectAttributes){
            if (this.iAirCraftService.findByIdAirCraft(id)!=null){
                this.iAirCraftService.findByIdAirCraft(id).setFlag(true);
                this.iAirCraftService.deleteAirCraft(this.iAirCraftService.findByIdAirCraft(id));
                redirectAttributes.addFlashAttribute("msg","Xóa thành công");
            }else {
                redirectAttributes.addFlashAttribute("msg","Không tồn tại đối tượng này");
            }

        return "redirect:/aircraft";
    }
}
