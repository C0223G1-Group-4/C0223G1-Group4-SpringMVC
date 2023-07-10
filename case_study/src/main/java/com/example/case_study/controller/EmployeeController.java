package com.example.case_study.controller;

import com.example.case_study.dto.EmployeeDto;
import com.example.case_study.model.Employees;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.employees_service.IEmployeesService;
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
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private IEmployeesService iEmployeesService;
    @Autowired
    private IAccountService iAccountService;

    @GetMapping("")
    public String showListEmployee(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        model.addAttribute("employeeList", iEmployeesService.findAll(pageable));
        model.addAttribute("accountUser", iAccountService.findAll());
        return "/employees/list_employee";
    }

    @GetMapping("/create-form")
    public String formCreateEmployee(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        model.addAttribute("accountUser", iAccountService.findAll());
        return "/employees/create_employee";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes) {
        new EmployeeDto().validate(employeeDto, bindingResult);
        model.addAttribute("accountUser", iAccountService.findAll());
        if (bindingResult.hasFieldErrors()) {
            return "/employees/create_employee";
        }
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeDto, employees);
        iEmployeesService.create(employees);
        redirectAttributes.addFlashAttribute("msg1", "Add employee new success!");
        return "redirect:/employee";
    }

    @GetMapping("/info/{id}")
    public String detailForm(Model model, @PathVariable Integer id) {
        model.addAttribute("employeess", iEmployeesService.findById(id));
//        model.addAttribute("accountUser", iAccountService.findAll());
        return "/employees/detail_employee";
    }

    @GetMapping("/edit/{id}")
    public String formUpdate(@PathVariable Integer id, Model model) {
        Employees employees = iEmployeesService.findById(id);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employees, employeeDto);
        model.addAttribute("employeeDto", employeeDto);
        model.addAttribute("accountUser", iAccountService.findAll());
        return "/employees/update_employee";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Validated EmployeeDto employeeDto,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("accountUser", iAccountService.findAll());
            return "/employees/update_employee";
        }
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeDto, employees);
        iEmployeesService.update(employees);
        redirectAttributes.addFlashAttribute("msg2", "Update employee new success!");
        return "redirect:/employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(value = "idDelete") Integer id, RedirectAttributes redirectAttributes) {
        iEmployeesService.delete(id);
        redirectAttributes.addFlashAttribute("msg3", "Delete employee new success!");
        return "redirect:/employee";
    }

    @GetMapping("/search-employee")
    public String search(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.DESC) Model model,
                         @RequestParam("email") String email,
                         @RequestParam("name") String name,
                         Pageable pageable) {
        model.addAttribute("accountUser", iAccountService.findAll());
        model.addAttribute("search", iEmployeesService.searchNameAndEmail(email,name,pageable));
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        return "/employees/list_employee";
    }

}
