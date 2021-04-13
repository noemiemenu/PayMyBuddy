package com.paymybuddy.repositories;

import com.paymybuddy.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountsRepository extends CrudRepository<BankAccount, Integer> {
}
