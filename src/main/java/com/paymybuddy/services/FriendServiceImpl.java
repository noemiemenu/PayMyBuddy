package com.paymybuddy.services;

import com.paymybuddy.repositories.FriendsRepository;
import com.paymybuddy.repositories.UsersRepository;
import com.paymybuddy.services.interfaces.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FriendServiceImpl implements FriendService {
    private final FriendsRepository friendsRepository;
    private final UsersRepository UsersRepository;

    @Override
    public void AddFriend() {

    }
}
