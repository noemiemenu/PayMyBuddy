package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * The interface Friends repository.
 */
@Repository
public interface FriendsRepository extends CrudRepository<Friend, Integer> {

    /**
     * Find friends by user id collection.
     *
     * @param userId the user id
     * @return the collection
     */
    Collection<Friend> findFriendsByUserId(int userId);
}
