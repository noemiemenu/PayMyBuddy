package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.BankAccountAlreadyCreatedException;
import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.models.User;

public interface ExternalBankAccountsService {
    void addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm, User user) throws BankAccountAlreadyCreatedException;

    void deleteBankAccount(int externalBankAccountId);
}
