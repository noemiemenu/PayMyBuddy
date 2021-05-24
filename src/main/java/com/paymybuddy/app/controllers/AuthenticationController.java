package com.paymybuddy.app.controllers;

import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@AllArgsConstructor
@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @GetMapping("/login")
    public String showLogInPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterPage(@RequestParam(required = false) String error, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new User());
            return "register";
        }
        return "redirect:/";
    }

    @PostMapping("/user/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        try {
            authenticationService.registerUser(user);
        } catch (UserAlreadyCreatedException e) {
            redirectAttributes.addAttribute("account_already_exists", true);
            return "redirect:/register";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return "redirect:/login";
    }
}