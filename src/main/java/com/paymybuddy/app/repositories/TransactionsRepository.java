package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {


}
