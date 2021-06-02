package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.InsufficientBalanceException;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.forms.SendMoneyToFriendForm;
import com.paymybuddy.app.forms.TransactionForm;
import com.paymybuddy.app.models.User;

public interface TransactionService {

    void sendToExternalBankAccount(TransactionForm transactionForm, User user) throws NegativeTransactionAmountException, InsufficientBalanceException;

    void addToInternalAccount(TransactionForm transactionForm, User user) throws NegativeTransactionAmountException;

    void sendToFriend(SendMoneyToFriendForm sendMoneyToFriendForm, User user) throws NegativeTransactionAmountException, InsufficientBalanceException;
}
