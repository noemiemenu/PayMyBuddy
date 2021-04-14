package com.paymybuddy.controllers;


import com.paymybuddy.services.interfaces.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class FriendController {
    private final FriendService friendService;

    public String showAddFriendPage(){return null;}
    public void AddFriend(){}
}
