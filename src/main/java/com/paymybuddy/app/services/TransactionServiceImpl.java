package com.paymybuddy.app.services;

import com.paymybuddy.app.enums.TransactionType;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.forms.TransactionForm;
import com.paymybuddy.app.models.ExternalBankAccount;
import com.paymybuddy.app.models.InternalBankAccount;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
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


    public void computeTransactionAmountWithFee(Transaction transaction, TransactionForm transactionForm) {
        transaction.setAmount(transactionForm.getAmount() * 0.995);
    }


    @Override
    public void addToInternalAccount(TransactionForm transactionForm, User user) throws NegativeTransactionAmountException {

        if (transactionForm.getAmount() <= 0){
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
        transaction.setSender(user);
        transaction.setType(TransactionType.ADD_TO_INTERNAL_ACCOUNT);
        computeTransactionAmountWithFee(transaction, transactionForm);
        transactionsRepository.save(transaction);

        userInternalBankAccount.setBalance(userInternalBankAccount.getBalance()+ transaction.getAmount());
        internalBankAccountRepository.save(userInternalBankAccount);
    }

    @Override
    public void sendToExternalBankAccount(TransactionForm transactionForm, User user) {
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
        transaction.setSender(user);
        transaction.setType(TransactionType.SEND_TO_BANK);
        computeTransactionAmountWithFee(transaction, transactionForm);
        transactionsRepository.save(transaction);

        userInternalBankAccount.setBalance(userInternalBankAccount.getBalance()-transaction.getAmount());
        internalBankAccountRepository.save(userInternalBankAccount);
    }

    @Override
    public void sendToFriend() {

    }
}
