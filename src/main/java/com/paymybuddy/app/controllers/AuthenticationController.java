package com.paymybuddy.app.controllers;

import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@AllArgsConstructor
@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UsersRepository usersRepository;


    @GetMapping("/login")
    public String showLogInPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String showRegisterPage(@RequestParam(required = false) String error, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            model.addAttribute("user", new User());
            return "register";
        }
        return "redirect:/home";
    }

    @PostMapping("/user/register")
    public String registerUser(User user) {
        authenticationService.registerUser(user);

        return "Registration ok.";
    }

    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return "redirect:/login";
    }
}