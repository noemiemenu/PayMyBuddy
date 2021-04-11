package com.paymybuddy.controllers;

import com.paymybuddy.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserController {
    private final UserService userService;
}
