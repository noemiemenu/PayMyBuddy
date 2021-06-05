package com.paymybuddy.app.repositories;

import com.paymybuddy.app.models.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FriendsRepository extends CrudRepository<Friend, Integer> {

    Collection<Friend> findFriendsByUserId(int userId);
}
