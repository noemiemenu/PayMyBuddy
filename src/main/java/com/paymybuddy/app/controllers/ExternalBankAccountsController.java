package com.paymybuddy.app.controllers;


import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.services.interfaces.ExternalBankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class ExternalBankAccountsController {
    private final ExternalBankAccountsService externalBankAccountsService;

    public String showAccountsPage(){return null;}


    @PostMapping("/bank/new")
    public String addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm){
            externalBankAccountsService.addBankAccount(newExternalBankAccountForm);
            return "redirect:/profile";
    }

    @DeleteMapping("/bank/delete/{externalBankAccountId}")
    public String deleteBankAccount(@PathVariable int externalBankAccountId) {
        externalBankAccountsService.deleteBankAccount(externalBankAccountId);
        return "redirect:/profile";
    }

}
