package com.example.case_study.controller;

import com.example.case_study.dto.AccountUserDto;
import com.example.case_study.dto.PassengerDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Passengers;
import com.example.case_study.model.RoleUser;
import com.example.case_study.service.account.IAccountService;
import com.example.case_study.service.employees_service.IEmployeesService;
import com.example.case_study.service.passengers_service.IPassengersService;
import com.example.case_study.service.post_service.IPostService;
import com.example.case_study.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class LoginController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPassengersService passengersService;
    @Autowired
    private IEmployeesService employeesService;
    @Autowired
    private IPostService postService;

    @GetMapping("/login")
    public String formLogin(@RequestParam(value = "error", required = false) boolean error,Principal principal, Model model) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"anonymousUser".equals(authentication)){
            return "redirect:/";
        }
        if (error) {
            model.addAttribute("msg", "* Email or password error *");
        }
        model.addAttribute("accountDto", new AccountUserDto());
        model.addAttribute("passengerDto", new PassengerDto());
        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            SecurityContextHolder.clearContext();
            redirectAttributes.addFlashAttribute("message","successful logout");
        }
        return "redirect:/";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        AccountUser accountUser = accountService.findByEmail(principal.getName());
        model.addAttribute("acc", accountUser);
        model.addAttribute("post", postService.findAll());
        if (accountUser.getRoleUser().getName().equals("ROLE_Customer")) {
            if (!passengersService.findByEmail(accountUser.getEmail()).isEnabled()) {
                redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
                return "redirect:/login";
            } else {
                model.addAttribute("info", passengersService.findByIdAccount(accountUser.getId()));
                return "home/index";
            }
        } else if (accountUser.getRoleUser().getName().equals("ROLE_Employee")) {
            model.addAttribute("info",employeesService.findByIdAccount(accountUser.getId()));
            return "home/index";
        } else {
            System.out.println("User Name: " + userName);
            model.addAttribute("info",employeesService.findByIdAccount(accountUser.getId()));
            return "home/index";
        }
    }

    @GetMapping("/400")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            AccountUser accountUser = accountService.findByEmail(principal.getName());
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + " You do not have permission to access this page!";
            model.addAttribute("info", passengersService.findByIdAccount(accountUser.getId()));
            model.addAttribute("message", message);
        }
        return "400Page";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute PassengerDto passengerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        if (passengersService.findByEmail(passengerDto.getAccountUserDto().getEmail()) != null) {
            redirectAttributes.addFlashAttribute("msg", "This email already exists");
        } else {
            passengerDto.setExpiryDate(calculateExpiryDate());
            RoleUser roleUser = accountService.findRoleById(3);
            AccountUser accountUser = new AccountUser();
            BeanUtils.copyProperties(passengerDto.getAccountUserDto(), accountUser);
            accountUser.setRoleUser(roleUser);
            accountService.createAccount(accountUser);
            Passengers passengers = new Passengers();
            BeanUtils.copyProperties(passengerDto, passengers);
            passengers.setAccountUser(accountUser);
            passengersService.create(passengers);
            String siteURL = getSiteURL(request);
            passengersService.sendVerificationEmail(passengers, siteURL);
            redirectAttributes.addFlashAttribute("msg", "You have signed up successfully! Please check your email to verify your account.");
        }
        return "redirect:/login";
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
