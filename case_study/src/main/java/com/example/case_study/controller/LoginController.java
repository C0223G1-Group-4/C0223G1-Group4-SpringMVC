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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String formLogin(@RequestParam(value = "error", required = false) boolean error, Model model) {
        if (error) {
            model.addAttribute("msg", "* Email or password error *");
        }
        model.addAttribute("accountDto", new AccountUserDto());
        model.addAttribute("passengerDto", new PassengerDto());
        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logout(Model model) {
        return "home/index";
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

//    @GetMapping("/signup")
//    public String signup(Model model) {
//
//        return "loginPage";
//    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute PassengerDto passengerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        if (passengersService.findByEmail(passengerDto.getAccountUserDto().getEmail()) != null) {
            redirectAttributes.addFlashAttribute("fail", "This email already exists");
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
            redirectAttributes.addFlashAttribute("success", "You have signed up successfully! Please check your email to verify your account.");
        }
        return "redirect:/login";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        if (passengersService.verify(code)) {
            redirectAttributes.addFlashAttribute("success", "Congratulations, your account has been verified.");
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
        }
        return "redirect:/login";
    }

    @GetMapping("/email")
    public String email() {
        return "email_reset_pw";
    }

    @PostMapping("/confirm_email")
    public String confirm_email(@RequestParam("email") String email, HttpServletRequest request, RedirectAttributes redirectAttributes) throws MessagingException, UnsupportedEncodingException {

        Passengers passengers = passengersService.findByEmail(email);
        if (passengers == null) {
            redirectAttributes.addFlashAttribute("fail", "Sorry, this email not found.");
            return "redirect:/login";
        }
        passengers.setExpiryDate(calculateExpiryDate());
        passengersService.reset(passengers);
        String siteURL = getSiteURL(request);
        passengersService.sendVerificationReset(passengers, siteURL);
        redirectAttributes.addFlashAttribute("success", "Please check your email to verify your account.");
        return "redirect:/login";
    }

    @GetMapping("/reset_pw")
    public String reset_pw(@ModelAttribute Passengers passengers, Model model) {
//        model.addAttribute("passengers",passengers);
        return "reset_pw";
    }

    @GetMapping("/verify_reset")
    public String verifyReset(@RequestParam("code") String code, Model model, RedirectAttributes redirectAttributes) {
        String email = null;
        if (passengersService.findByCode(code) != null) {
            Passengers passengers = passengersService.findByCode(code);
            email = passengers.getAccountUser().getEmail();
        }
        if (passengersService.verifyReset(code)) {
            Passengers passenger = passengersService.findByEmail(email);
            model.addAttribute("passengers", passenger);
            return "reset_pw";
//            redirectAttributes.addFlashAttribute("success", "Congratulations, your account has been verified.");
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
            return "redirect:/login";
        }
    }

    @PostMapping("/new_pw")
    public String new_pw(@RequestParam("new_pw") String new_pw, @ModelAttribute Passengers passengers, RedirectAttributes redirectAttributes) {
        passengersService.reset_pw(passengers, new_pw);
        redirectAttributes.addFlashAttribute("success", "Password change successful.");
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
