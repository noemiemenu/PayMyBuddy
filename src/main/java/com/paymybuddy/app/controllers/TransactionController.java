package com.paymybuddy.app.controllers;


import com.paymybuddy.app.forms.TransactionForm;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.InternalBankAccountRepository;
import com.paymybuddy.app.repositories.TransactionsRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthenticationService authenticationService;
    private final TransactionsRepository transactionsRepository;
    private final InternalBankAccountRepository internalBankAccountRepository;


    @GetMapping(value = "/transaction")
    public String TransactionPage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("externalBankAccounts", user.getExternalBankAccounts());
        model.addAttribute("receiveTransactions", user.getReceiveTransactions());
        model.addAttribute("sentTransactions", user.getSentTransactions());
        model.addAttribute("balance", user.getInternalBankAccount().getBalance());
        return "transaction";
    }

    @PostMapping("/transaction/external/new")
    public String addMoneyToInternalAccount(TransactionForm transactionForm, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        transactionService.addToInternalAccount(transactionForm, user);

        return "redirect:/transaction";
    }

    @PostMapping("/transaction/external/send")
    public String sendMoneyToExternalBankAccount(TransactionForm transactionForm, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        transactionService.sendToExternalBankAccount(transactionForm, user);
        return "redirect:/transaction";
    }


    public void sendToFriend() {

    }

}
