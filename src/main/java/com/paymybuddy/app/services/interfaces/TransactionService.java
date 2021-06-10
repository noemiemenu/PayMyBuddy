package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.AmountFormatException;
import com.paymybuddy.app.exceptions.InsufficientBalanceException;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.forms.AbstractTransactionForm;
import com.paymybuddy.app.forms.AddMoneyToBalanceForm;
import com.paymybuddy.app.forms.SendMoneyToFriendForm;
import com.paymybuddy.app.models.User;

/**
 * The interface Transaction service.
 */
public interface TransactionService {


    /**
     * Add to internal account.
     *
     * @param addMoneyToBalanceForm the transaction form
     * @param user            the user
     * @throws NegativeTransactionAmountException the negative transaction amount exception
     */
    void addToInternalAccount(AddMoneyToBalanceForm addMoneyToBalanceForm, User user) throws NegativeTransactionAmountException, AmountFormatException;

    /**
     * Send to friend.
     *
     * @param sendMoneyToFriendForm the send money to friend form
     * @param user                  the user
     * @throws NegativeTransactionAmountException the negative transaction amount exception
     * @throws InsufficientBalanceException       the insufficient balance exception
     */
    void sendToFriend(SendMoneyToFriendForm sendMoneyToFriendForm, User user) throws NegativeTransactionAmountException, InsufficientBalanceException, AmountFormatException;

    /**
     * Compute transaction amount with fee double.
     *
     * @param user            the user
     * @param transactionForm the transaction form
     * @return the double
     */
    double computeTransactionAmountWithFee(User user, AbstractTransactionForm transactionForm);
}
