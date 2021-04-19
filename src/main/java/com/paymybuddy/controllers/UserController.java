package com.paymybuddy.controllers;


import com.paymybuddy.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    public String showProfilePage(){return null;}

    public void changeProfileInfo(){}

    public String showHomePage(){return null;}

}
