package com.paymybuddy.controllers;

import com.paymybuddy.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public String showRegisterPage() {
        return null;
    }

    public void registerUser() { }

    public String showLogInPage(){
        return null;
    }

    public void logIn(){ }

    public void logOff(){ }



}