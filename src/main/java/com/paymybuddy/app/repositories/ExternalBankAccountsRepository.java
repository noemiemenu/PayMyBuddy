package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.ExternalBankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ExternalBankAccountsRepository extends CrudRepository<ExternalBankAccount, Integer> {

    Collection<ExternalBankAccount> findExternalBankAccountByUserId(int id);
}
