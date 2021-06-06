package com.paymybuddy.app.controllers;


import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class UserController {
    private final AuthenticationService authenticationService;


    @GetMapping("/")
    public String showHomePage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("user", user);
        model.addAttribute("friends", user.getFriends());
        return "homePage";

    }

    @GetMapping(value = "/profile")
    public String showProfilePage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("user", user);
        return "profile";
    }

}
