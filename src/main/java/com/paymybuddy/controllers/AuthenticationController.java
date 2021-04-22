package com.paymybuddy.controllers;

import com.paymybuddy.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@AllArgsConstructor
@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/register")
    public String showRegisterPage(@RequestParam(required = false) String error) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "register";
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String showLogInPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/home";
    }

    public void registerUser() { }

    public void logIn(){ }

    public void logOff(){ }



}