package com.paymybuddy.app.services;

import com.paymybuddy.app.enums.TransactionType;
import com.paymybuddy.app.exceptions.InsufficientBalanceException;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.forms.AbstractTransactionForm;
import com.paymybuddy.app.forms.SendMoneyToFriendForm;
import com.paymybuddy.app.forms.TransactionForm;
import com.paymybuddy.app.models.ExternalBankAccount;
import com.paymybuddy.app.models.InternalBankAccount;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.FriendsRepository;
import com.paymybuddy.app.repositories.InternalBankAccountRepository;
import com.paymybuddy.app.repositories.TransactionsRepository;
import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final InternalBankAccountRepository internalBankAccountRepository;
    private final FriendsRepository friendsRepository;


    public double computeTransactionAmountWithFee(User user, AbstractTransactionForm transactionForm) {
        return user.getInternalBankAccount().getBalance() - (transactionForm.getAmount() * 1.005);
    }


    @Override
    public void addToInternalAccount(TransactionForm transactionForm, User user) throws NegativeTransactionAmountException {

        if (transactionForm.getAmount() <= 0) {
            throw new NegativeTransactionAmountException("The amount cannot be negative or equals to 0.");
        }

        InternalBankAccount userInternalBankAccount = user.getInternalBankAccount();
        ExternalBankAccount userExternalBankAccount = user.getExternalBankAccounts()
                .stream()
                .filter((ExternalBankAccount externalBankAccount)
                        -> transactionForm.getExternalBankAccountId() == externalBankAccount.getId())
                .findAny().get();

        Transaction transaction = new Transaction();
        transaction.setWording(transactionForm.getWording());
        transaction.setDate(LocalDateTime.now());
        transaction.setInternalBankAccount(userInternalBankAccount);
        transaction.setExternalBankAccount(userExternalBankAccount);
        transaction.setReceiver(user);
        transaction.setType(TransactionType.ADD_TO_INTERNAL_ACCOUNT);
        transaction.setAmount(transactionForm.getAmount() * 0.995);
        transactionsRepository.save(transaction);

        userInternalBankAccount.setBalance(userInternalBankAccount.getBalance() + transaction.getAmount());
        internalBankAccountRepository.save(userInternalBankAccount);
    }

    @Override
    public void sendToExternalBankAccount(TransactionForm transactionForm, User user) throws NegativeTransactionAmountException, InsufficientBalanceException {

        if (transactionForm.getAmount() <= 0) {
            throw new NegativeTransactionAmountException("The amount cannot be negative or equals to 0.");
        }

        if (computeTransactionAmountWithFee(user, transactionForm) < 0) {
            throw new InsufficientBalanceException("the account balance is insufficient: " + user.getInternalBankAccount().getBalance());
        }

        InternalBankAccount userInternalBankAccount = user.getInternalBankAccount();

        ExternalBankAccount userExternalBankAccount = user.getExternalBankAccounts()
                .stream()
                .filter((ExternalBankAccount externalBankAccount)
                        -> transactionForm.getExternalBankAccountId() == externalBankAccount.getId())
                .findAny().get();

        Transaction transaction = new Transaction();
        transaction.setWording(transactionForm.getWording());
        transaction.setDate(LocalDateTime.now());
        transaction.setInternalBankAccount(userInternalBankAccount);
        transaction.setExternalBankAccount(userExternalBankAccount);
        transaction.setAmount(transactionForm.getAmount());
        transaction.setReceiver(user);
        transaction.setType(TransactionType.SEND_TO_BANK);
        transactionsRepository.save(transaction);

        userInternalBankAccount.setBalance(computeTransactionAmountWithFee(user, transactionForm));
        internalBankAccountRepository.save(userInternalBankAccount);
    }

    @Override
    public void sendToFriend(SendMoneyToFriendForm sendMoneyToFriendForm, User user) throws NegativeTransactionAmountException, InsufficientBalanceException {

        if (sendMoneyToFriendForm.getAmount() <= 0) {
            throw new NegativeTransactionAmountException("The amount cannot be negative or equals to 0.");
        }
        if (computeTransactionAmountWithFee(user, sendMoneyToFriendForm) < 0) {
            throw new InsufficientBalanceException("the account balance is insufficient: " + user.getInternalBankAccount().getBalance());
        }

        User userFriend = user.getFriends()
                .stream()
                .filter(friend -> friend.getFriendUser().getEmail().equals(sendMoneyToFriendForm.getFriendEmail()))
                .findAny()
                .get()
                .getFriendUser();

        InternalBankAccount userInternalBankAccount = user.getInternalBankAccount();

        InternalBankAccount friendInternalBankAccount = userFriend.getInternalBankAccount();

        Transaction transaction = new Transaction();
        transaction.setWording(sendMoneyToFriendForm.getWording());
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(sendMoneyToFriendForm.getAmount());
        transaction.setReceiver(userFriend);
        transaction.setSender(user);
        transaction.setType(TransactionType.SEND_TO_FRIEND);
        transactionsRepository.save(transaction);

        userInternalBankAccount.setBalance(computeTransactionAmountWithFee(user, sendMoneyToFriendForm));
        internalBankAccountRepository.save(userInternalBankAccount);

        friendInternalBankAccount.setBalance(friendInternalBankAccount.getBalance() + sendMoneyToFriendForm.getAmount());
        internalBankAccountRepository.save(friendInternalBankAccount);
    }
}
