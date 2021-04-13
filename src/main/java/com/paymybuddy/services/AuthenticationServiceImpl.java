package com.paymybuddy.services;

import com.paymybuddy.repositories.BankAccountsRepository;
import com.paymybuddy.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    final UsersRepository UsersRepository;
    final BankAccountsRepository bankAccountsRepository;

    public void registerUser(){

    }
}
