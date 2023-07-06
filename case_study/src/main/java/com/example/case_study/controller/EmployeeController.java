package com.example.case_study.controller;

import com.example.case_study.dto.EmployeeDto;
import com.example.case_study.model.Employees;
import com.example.case_study.service.employees_service.IEmployeesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private IEmployeesService iEmployeesService;

    @GetMapping("")
    public String showListEmployee(@PageableDefault(value = 5,sort = "id", direction = Sort.Direction.DESC)
                                   Pageable pageable, @RequestParam(value = "search",defaultValue = "")
                                   String search, Model model){
        model.addAttribute("employeeList",iEmployeesService.findAll(search, pageable));
        model.addAttribute("search", search);
        return "/list_employee";
    }
    @GetMapping("/create-form")
    public String formCreateEmployee(Model model){
        model.addAttribute("employeeDto", new EmployeeDto());
//        model.addAttribute("123",iEmployeesService.findAll());
        return "/create_employee";
    }
    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes){
        new EmployeeDto().validate(employeeDto,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/create_employee";
        }
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeDto, employees);
        iEmployeesService.create(employees);
        redirectAttributes.addFlashAttribute("msg1","Add employee new success!");
        return "redirect:/employee";
    }

}
