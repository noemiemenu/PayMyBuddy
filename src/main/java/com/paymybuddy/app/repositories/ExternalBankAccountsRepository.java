package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.ExternalBankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * The interface External bank accounts repository.
 */
@Repository
public interface ExternalBankAccountsRepository extends CrudRepository<ExternalBankAccount, Integer> {

    /**
     * Find external bank accounts by user id collection.
     *
     * @param id the id
     * @return the collection
     */
    Collection<ExternalBankAccount> findExternalBankAccountsByUserId(int id);
}
