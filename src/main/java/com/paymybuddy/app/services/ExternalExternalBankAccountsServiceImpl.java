package com.paymybuddy.app.services;

import com.paymybuddy.app.exceptions.BankAccountAlreadyCreatedException;
import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.models.ExternalBankAccount;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.ExternalBankAccountsRepository;
import com.paymybuddy.app.services.interfaces.ExternalBankAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExternalExternalBankAccountsServiceImpl implements ExternalBankAccountsService {
    private final ExternalBankAccountsRepository externalBankAccountsRepository;


    @Override
    public void addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm, User user) throws BankAccountAlreadyCreatedException {
        if (user.getExternalBankAccounts().stream().anyMatch(externalBankAccount -> externalBankAccount.getName().equals(newExternalBankAccountForm.getBankAccountName()))){
            throw new BankAccountAlreadyCreatedException("Bank account already created");
        }

        ExternalBankAccount newExternalBankAccount = new ExternalBankAccount();
        newExternalBankAccount.setUser(user);
        newExternalBankAccount.setName(newExternalBankAccountForm.getBankAccountName());
        newExternalBankAccount.setRib(newExternalBankAccountForm.getRib());
        externalBankAccountsRepository.save(newExternalBankAccount);

    }

    @Override
    public void deleteBankAccount(int externalBankAccountId) {
        externalBankAccountsRepository.deleteById(externalBankAccountId);
    }
}
