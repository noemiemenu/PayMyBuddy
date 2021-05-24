package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.forms.NewExternalBankAccountForm;

public interface ExternalBankAccountsService {
    void addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm);

    void deleteBankAccount(int externalBankAccountId);
}
