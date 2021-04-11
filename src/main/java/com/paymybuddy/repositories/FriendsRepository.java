package com.paymybuddy.repositories;

import com.paymybuddy.models.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendsRepository extends CrudRepository<Friend, Integer> {
}
