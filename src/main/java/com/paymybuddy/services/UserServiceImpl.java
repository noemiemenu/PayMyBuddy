package com.paymybuddy.services;

import com.paymybuddy.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;
    private final AuthenticationService authenticationService;
}