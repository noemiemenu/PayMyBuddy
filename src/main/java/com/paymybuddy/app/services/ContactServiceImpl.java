package com.paymybuddy.app.services;

import com.paymybuddy.app.repositories.FriendsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private final UsersRepository userRepository;
    private final FriendsRepository friendsRepository;


}
