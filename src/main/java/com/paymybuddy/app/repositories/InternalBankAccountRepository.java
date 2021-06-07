package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.InternalBankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Internal bank account repository.
 */
@Repository
public interface InternalBankAccountRepository extends CrudRepository<InternalBankAccount, Integer> {


    /**
     * Find by user id internal bank account.
     *
     * @param userId the user id
     * @return the internal bank account
     */
    InternalBankAccount findByUserId(int userId);
}
