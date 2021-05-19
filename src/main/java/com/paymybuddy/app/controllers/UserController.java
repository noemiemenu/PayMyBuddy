package com.paymybuddy.app.controllers;


import com.paymybuddy.app.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping(value="/profile")
    public String showProfilePage(){

        return "profile";
    }

    public void changeProfileInfo(){
    }

    @GetMapping("/")
    public String showHomePage(){

        return "homePage";
    }

}
