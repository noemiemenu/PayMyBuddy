package com.paymybuddy.app.services;

import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.repositories.ExternalBankAccountsRepository;
import com.paymybuddy.app.services.interfaces.BankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BankAccountsServiceImpl implements BankAccountsService {
    private final ExternalBankAccountsRepository externalBankAccountsRepository;
    private final UsersRepository usersRepository;

    @Override
    public void addBankAccount() {

    }
}
