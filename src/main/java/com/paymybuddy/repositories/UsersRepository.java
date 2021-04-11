package com.paymybuddy.repositories;

import com.paymybuddy.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer> {
}
