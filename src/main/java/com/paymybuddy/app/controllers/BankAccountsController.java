package com.paymybuddy.app.controllers;


import com.paymybuddy.app.services.interfaces.BankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class BankAccountsController {
    private final BankAccountsService bankAccountsService;

    public String showAccountsPage(){return null;}
    public void addBankAccount(){ }


}
