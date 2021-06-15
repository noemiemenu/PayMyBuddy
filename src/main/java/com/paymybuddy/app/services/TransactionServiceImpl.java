package com.paymybuddy.app.services;

import com.paymybuddy.app.enums.TransactionType;
import com.paymybuddy.app.exceptions.AmountFormatException;
import com.paymybuddy.app.exceptions.InsufficientBalanceException;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.forms.AbstractTransactionForm;
import com.paymybuddy.app.forms.AddMoneyToBalanceForm;
import com.paymybuddy.app.forms.SendMoneyToFriendForm;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.TransactionsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * The type Transaction service.
 */
@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final UsersRepository usersRepository;

    private final double fees = 1.005;



    /**
     * Calculate the amount of the transaction with fees
     * @param user the user
     * @param abstractTransactionForm
     * @return
     */
    public double computeTransactionAmountWithFee(User user, AbstractTransactionForm abstractTransactionForm) {
        double amount = Double.parseDouble(abstractTransactionForm.getAmount());
        return user.getBalance() - (amount * fees);
    }

    /**
     * Is numeric boolean.
     *
     * @param strNum the str num
     * @return the boolean
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void addToInternalAccount(AddMoneyToBalanceForm addMoneyToBalanceForm, User user) throws NegativeTransactionAmountException, AmountFormatException {

        if (!isNumeric(addMoneyToBalanceForm.getAmount())){
            throw new AmountFormatException("The amount must be a number.");
        }

        double amount = Double.parseDouble(addMoneyToBalanceForm.getAmount());


        if (amount <= 0) {
            throw new NegativeTransactionAmountException("The amount cannot be negative or equals to 0.");
        }

        Transaction transaction = new Transaction();
        transaction.setLabel(addMoneyToBalanceForm.getWording());
        transaction.setDate(LocalDateTime.now());
        transaction.setReceiver(user);
        transaction.setType(TransactionType.ADD_TO_INTERNAL_ACCOUNT);
        transaction.setAmount(amount * 0.995);
        transactionsRepository.save(transaction);

        user.setBalance(user.getBalance() + transaction.getAmount());
        usersRepository.save(user);
    }


    @Override
    public void sendToFriend(SendMoneyToFriendForm sendMoneyToFriendForm, User user) throws NegativeTransactionAmountException, InsufficientBalanceException, AmountFormatException {

        if (!isNumeric(sendMoneyToFriendForm.getAmount())){
            throw new AmountFormatException("The amount must be a number.");
        }

        double amount = Double.parseDouble(sendMoneyToFriendForm.getAmount());

        if (amount <= 0) {
            throw new NegativeTransactionAmountException("The amount cannot be negative or equals to 0.");
        }

        if (computeTransactionAmountWithFee(user, sendMoneyToFriendForm) < 0) {
            throw new InsufficientBalanceException("the account balance is insufficient: " + user.getBalance());
        }

        User userFriend = user.getFriends()
                .stream()
                .filter(friend -> friend.getFriendUser().getEmail().equals(sendMoneyToFriendForm.getFriendEmail()))
                .findAny()
                .get()
                .getFriendUser();


        Transaction transaction = new Transaction();
        transaction.setLabel(sendMoneyToFriendForm.getWording());
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setReceiver(userFriend);
        transaction.setSender(user);
        transaction.setType(TransactionType.SEND_TO_FRIEND);
        transactionsRepository.save(transaction);

        user.setBalance(computeTransactionAmountWithFee(user, sendMoneyToFriendForm));
        usersRepository.save(user);

        userFriend.setBalance(userFriend.getBalance() + amount);
        usersRepository.save(userFriend);
    }
}
