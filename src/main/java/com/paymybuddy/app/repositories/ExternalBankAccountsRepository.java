package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.ExternalBankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalBankAccountsRepository extends CrudRepository<ExternalBankAccount, Integer> {


}