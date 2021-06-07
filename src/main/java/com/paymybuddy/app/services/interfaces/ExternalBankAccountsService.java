package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.BankAccountAlreadyCreatedException;
import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.models.User;

/**
 * The interface External bank accounts service.
 */
public interface ExternalBankAccountsService {
    /**
     * Add bank account.
     *
     * @param newExternalBankAccountForm the new external bank account form
     * @param user                       the user
     * @throws BankAccountAlreadyCreatedException the bank account already created exception
     */
    void addBankAccount(NewExternalBankAccountForm newExternalBankAccountForm, User user) throws BankAccountAlreadyCreatedException;

    /**
     * Delete bank account.
     *
     * @param externalBankAccountId the external bank account id
     */
    void deleteBankAccount(int externalBankAccountId);
}
