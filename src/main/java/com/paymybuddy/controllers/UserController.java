package com.paymybuddy.controllers;

import com.paymybuddy.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;
}
