package com.paymybuddy.services;

import com.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final UsersRepository usersRepository;
}
