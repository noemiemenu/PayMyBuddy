package com.paymybuddy.app.controllers;


import com.paymybuddy.app.services.interfaces.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class ContactController {
    private final ContactService contactService;

    public String showContactPage() {
        return null;
    }

}