package com.paymybuddy.controllers;

import com.paymybuddy.services.FriendServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class FriendController {
    private final FriendServiceImpl friendServiceImpl;
}
