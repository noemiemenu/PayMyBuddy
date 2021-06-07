package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Users repository.
 */
@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {

    /**
     * Find user by email user.
     *
     * @param email the email
     * @return the user
     */
    User findUserByEmail(String email);

}
