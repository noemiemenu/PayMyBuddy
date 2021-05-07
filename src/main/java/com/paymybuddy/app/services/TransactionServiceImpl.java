package com.paymybuddy.app.services;

import com.paymybuddy.app.repositories.TransactionRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UsersRepository usersRepository;

    @Override
    public void sendToBank() {

    }

    @Override
    public void addToAccount() {

    }

    @Override
    public void sendToFriend() {

    }
}
