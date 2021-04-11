package com.paymybuddy.controllers;

import com.paymybuddy.services.BankAccountsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankAccountsController {
    private final BankAccountsService bankAccountsRepository;
}
