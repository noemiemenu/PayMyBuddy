package com.paymybuddy.controllers;

import com.paymybuddy.services.ContactServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class ContactController {
    private final ContactServiceImpl contactServiceImpl;

    public String showContactPage(){return null;}

    public void FriendsList(){}

}
