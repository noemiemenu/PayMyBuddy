package com.paymybuddy.services;

import com.paymybuddy.repositories.FriendsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private final com.paymybuddy.repositories.UsersRepository userRepository;
    private final FriendsRepository friendsRepository;
}
