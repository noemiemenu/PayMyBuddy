package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {

    User findUserByEmail(String email);
}
