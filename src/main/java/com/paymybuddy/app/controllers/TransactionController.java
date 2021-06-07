package com.paymybuddy.app.controllers;


import com.paymybuddy.app.exceptions.InsufficientBalanceException;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.forms.SendMoneyToFriendForm;
import com.paymybuddy.app.forms.TransactionForm;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Transaction controller.
 */
@AllArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthenticationService authenticationService;


    /**
     * Transaction page string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping(value = "/transaction")
    public String transactionPage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        Collection<Transaction> transactions = Stream.concat(
                user.getReceiveTransactions().stream(),
                user.getSentTransactions().stream()
        )
        .sorted(Comparator.comparing((Transaction transaction) -> transaction.getDate()).reversed())
        .collect(Collectors.toList());

        model.addAttribute("currentUserId", user.getId());
        model.addAttribute("externalBankAccounts", user.getExternalBankAccounts());
        model.addAttribute("transactions", transactions);
        model.addAttribute("balance", user.getInternalBankAccount().getBalance());
        model.addAttribute("friends", user.getFriends());

        return "transaction";
    }

    /**
     * Add money to internal account string.
     *
     * @param transactionForm    the transaction form
     * @param request            the request
     * @param redirectAttributes the redirect attributes
     * @return redirect to the transaction Page
     */
    @PostMapping("/transaction/external/new")
    public String addMoneyToInternalAccount(TransactionForm transactionForm, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = authenticationService.getCurrentLoggedUser(request);
        try {
            transactionService.addToInternalAccount(transactionForm, user);
        } catch (NegativeTransactionAmountException e) {
            redirectAttributes.addAttribute("error_new_external_transaction", e.getMessage());
        }

        return "redirect:/transaction";
    }

    /**
     * Send money to external bank account string.
     *
     * @param transactionForm    the transaction form
     * @param request            the request
     * @param redirectAttributes the redirect attributes
     * @return return redirect to the transaction Page
     */
    @PostMapping("/transaction/external/send")
    public String sendMoneyToExternalBankAccount(TransactionForm transactionForm, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = authenticationService.getCurrentLoggedUser(request);
        try {
            transactionService.sendToExternalBankAccount(transactionForm, user);
        } catch (NegativeTransactionAmountException | InsufficientBalanceException e) {
            redirectAttributes.addAttribute("error_send_to_bank_account", e.getMessage());
        }
        return "redirect:/transaction";
    }


    /**
     * Send to friend string.
     *
     * @param sendMoneyToFriendForm the send money to friend form
     * @param request               the request
     * @param redirectAttributes    the redirect attributes
     * @return return redirect to the transaction Page
     */
    @PostMapping("/transaction/friend/new")
    public String sendToFriend(SendMoneyToFriendForm sendMoneyToFriendForm, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = authenticationService.getCurrentLoggedUser(request);
        try {
            transactionService.sendToFriend(sendMoneyToFriendForm, user);
        } catch (NegativeTransactionAmountException | InsufficientBalanceException e) {
            redirectAttributes.addAttribute("error_send_to_friend",e.getMessage());
        }

        return "redirect:/transaction";
    }

}
