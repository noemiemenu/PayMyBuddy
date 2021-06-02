package com.paymybuddy.app.controllers;


import com.paymybuddy.app.exceptions.BankAccountAlreadyCreatedException;
import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.ExternalBankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class ExternalBankAccountsController {
    private final ExternalBankAccountsService externalBankAccountsService;
    private final AuthenticationService authenticationService;


    @PostMapping("/bank/new")
    public String addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = authenticationService.getCurrentLoggedUser(request);
        try {
            externalBankAccountsService.addBankAccount(newExternalBankAccountForm, user);
        } catch (BankAccountAlreadyCreatedException e) {
            redirectAttributes.addAttribute("error_bank_account_already_created", e);
        }
        return "redirect:/profile";
    }

    @DeleteMapping("/bank/delete/{externalBankAccountId}")
    public String deleteBankAccount(@PathVariable int externalBankAccountId) {
        externalBankAccountsService.deleteBankAccount(externalBankAccountId);
        return "redirect:/profile";
    }

}
