package com.paymybuddy.services;

import com.paymybuddy.repositories.BankAccountsRepository;
import com.paymybuddy.repositories.UsersRepository;
import com.paymybuddy.services.interfaces.BankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BankAccountsServiceImpl implements BankAccountsService {
    private final BankAccountsRepository bankAccountsRepository;
    private final UsersRepository UsersRepository;

    @Override
    public void addBankAccount() {

    }
}
