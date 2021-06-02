package com.paymybuddy.app;

import com.paymybuddy.app.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FriendTests {

    @Autowired
    private UsersRepository usersRepository;



}
