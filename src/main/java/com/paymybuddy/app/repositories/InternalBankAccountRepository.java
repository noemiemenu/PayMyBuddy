package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.InternalBankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalBankAccountRepository extends CrudRepository<InternalBankAccount, Integer> {


    InternalBankAccount findByUserId(int userId);
}
