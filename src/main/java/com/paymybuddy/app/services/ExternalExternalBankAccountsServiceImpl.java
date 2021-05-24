package com.paymybuddy.app.services;

import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.models.ExternalBankAccount;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.ExternalBankAccountsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.ExternalBankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExternalExternalBankAccountsServiceImpl implements ExternalBankAccountsService {
    private final ExternalBankAccountsRepository externalBankAccountsRepository;
    private final UsersRepository usersRepository;
    private final AuthenticationServiceImpl authenticationService;

    @Override
    public void addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm, User user) {
        ExternalBankAccount externalBankAccount = new ExternalBankAccount();
        externalBankAccount.setUser(user);
        externalBankAccount.setName(newExternalBankAccountForm.getBankAccountName());
        externalBankAccount.setRib(newExternalBankAccountForm.getRib());
        externalBankAccountsRepository.save(externalBankAccount);

    }

    @Override
    public void deleteBankAccount(int externalBankAccountId) {
        externalBankAccountsRepository.deleteById(externalBankAccountId);
    }
}
