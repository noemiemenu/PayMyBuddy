package com.paymybuddy.repositories;

import com.paymybuddy.models.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends CrudRepository<Friend, Integer> {
}
