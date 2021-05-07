package com.paymybuddy.app.controllers;


import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;

    public String showTransactionPage(){
        return null;
    }

    public void sendToBank(){

    }
    public void addToAccount(){

    }
    public void sendToFriend(){

    }

}
