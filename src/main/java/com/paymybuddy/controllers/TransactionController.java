package com.paymybuddy.controllers;

import com.paymybuddy.services.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class TransactionController {
    private final TransactionServiceImpl transactionServiceImpl;

}
