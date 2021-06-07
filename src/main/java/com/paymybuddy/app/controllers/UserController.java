package com.paymybuddy.app.controllers;


import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * The type User controller.
 */
@AllArgsConstructor
@Controller
public class UserController {
    private final AuthenticationService authenticationService;


    /**
     * Show home page string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping("/")
    public String showHomePage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("user", user);
        model.addAttribute("friends", user.getFriends());
        return "homePage";

    }

    /**
     * Show profile page string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping(value = "/profile")
    public String showProfilePage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("user", user);
        return "profile";
    }

}
