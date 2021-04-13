package com.paymybuddy.controllers;

import com.paymybuddy.services.BankAccountsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class BankAccountsController {
    private final BankAccountsServiceImpl bankAccountsRepository;
}
