package com.paymybuddy.app.services;

import com.paymybuddy.app.repositories.FriendsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.FriendService;
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
