package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountsRepository extends CrudRepository<BankAccount, Integer> {
}
