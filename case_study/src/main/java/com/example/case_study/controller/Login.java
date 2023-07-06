package com.example.case_study.controller;

import com.example.case_study.dto.AccountUserDto;
import com.example.case_study.dto.PassengerDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Passengers;
import com.example.case_study.model.RoleUser;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.employees_service.IEmployeesService;
import com.example.case_study.service.passengers_service.IPassengersService;
import com.example.case_study.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

@Controller
public class Login {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPassengersService passengersService;
    @Autowired
    private IEmployeesService employeesService;
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
    @GetMapping("/login")
    public String formLogin(Model model) {
        model.addAttribute("accountDto", new AccountUserDto());
        return "loginPage1";
    }

    @GetMapping("/logoutSuccessful")
    public String logout(Model model) {
        return "index";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal ) {
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        AccountUser accountUser = accountService.findByEmail(principal.getName());
        model.addAttribute("acc",accountUser);
        if (accountUser.getRoleUser().getName().equals("ROLE_Customer")){
            model.addAttribute("info",passengersService.findByIdAccount(accountUser.getId()));
            return "index";
        } else if(accountUser.getRoleUser().getName().equals("ROLE_Employee")){
//            model.addAttribute("info",employeesService.findByIdAccount(accountUser.getId()));
            return "redirect:/passenger";
        }else {
            System.out.println("User Name: " + userName);
//            model.addAttribute("info",employeesService.findByIdAccount(accountUser.getId()));
            return "redirect:/employee";
        }

    }

    @GetMapping("/400")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "400Page";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("passengerDto", new PassengerDto());
        return "loginPage";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute PassengerDto passengerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes,HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        if(passengersService.findByEmail(passengerDto.getAccountUserDto().getEmail())!=null){
            redirectAttributes.addFlashAttribute("msg","This email already exists");
        }else{
            passengerDto.setExpiryDate(calculateExpiryDate());
            RoleUser roleUser =accountService.findRoleById(3);
            AccountUser accountUser=new AccountUser();
            BeanUtils.copyProperties(passengerDto.getAccountUserDto(),accountUser);
            accountUser.setRoleUser(roleUser);
            accountService.createAccount(accountUser);
            Passengers passengers=new Passengers();
            BeanUtils.copyProperties(passengerDto, passengers);
            passengers.setAccountUser(accountUser);
            passengersService.create(passengers);
            String siteURL = getSiteURL(request);
            passengersService.sendVerificationEmail(passengers, siteURL);
            redirectAttributes.addFlashAttribute("msg","You have signed up successfully! Please check your email to verify your account.");
        }
        return "redirect:/loginPage";
    }





    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 1);
        return new Date(cal.getTime().getTime());
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }


}
