package com.paymybuddy.services;

import com.paymybuddy.repositories.BankAccountsRepository;
import com.paymybuddy.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UsersRepository UsersRepository;
    private final BankAccountsRepository bankAccountsRepository;
}
