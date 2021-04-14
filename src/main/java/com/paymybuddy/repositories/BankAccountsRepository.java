package com.paymybuddy.repositories;

import com.paymybuddy.models.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountsRepository extends CrudRepository<BankAccount, Integer> {
}
