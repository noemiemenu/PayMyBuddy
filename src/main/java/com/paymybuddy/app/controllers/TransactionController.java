package com.paymybuddy.app.controllers;


import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping(value = "/transaction")
    public String showTransactionPage() {
        return "transaction";
    }

    public void sendToBank() {

    }

    public void addToAccount() {

    }

    public void sendToFriend() {

    }

}
