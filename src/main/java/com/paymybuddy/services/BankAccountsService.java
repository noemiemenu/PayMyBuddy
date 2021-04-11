package com.paymybuddy.services;

import com.paymybuddy.repositories.BankAccountsRepository;
import com.paymybuddy.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BankAccountsService {
    private final BankAccountsRepository bankAccountsRepository;
    private final UsersRepository UsersRepository;
}
