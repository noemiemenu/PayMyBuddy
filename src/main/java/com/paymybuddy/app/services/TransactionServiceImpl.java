package com.paymybuddy.app.services;

import com.paymybuddy.app.repositories.TransactionsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionsRepository transactionsRepository;
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
