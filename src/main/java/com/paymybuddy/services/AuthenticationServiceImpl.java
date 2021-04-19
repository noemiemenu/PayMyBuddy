package com.paymybuddy.services;

import com.paymybuddy.repositories.BankAccountsRepository;
import com.paymybuddy.repositories.UsersRepository;
import com.paymybuddy.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    final UsersRepository UsersRepository;
    final BankAccountsRepository bankAccountsRepository;


    @Override
    public void registerUser() {

    }

    @Override
    public void logIn() {

    }

    @Override
    public void logOff() {

    }
}
