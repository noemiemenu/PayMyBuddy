package com.paymybuddy.app.services;

import com.paymybuddy.app.repositories.BankAccountsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsersRepository UsersRepository;
    private final BankAccountsRepository bankAccountsRepository;


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
