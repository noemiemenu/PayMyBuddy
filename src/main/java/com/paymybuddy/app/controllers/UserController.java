package com.paymybuddy.app.controllers;


import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UsersRepository usersRepository;
    private final AuthenticationService authenticationService;


    @GetMapping("/")
    public String showHomePage() {
        return "homePage";

    }

    @GetMapping(value = "/profile")
    public String showProfilePage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("user", user);
        return "profile";
    }



}
