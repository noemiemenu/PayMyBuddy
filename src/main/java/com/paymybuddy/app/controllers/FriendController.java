package com.paymybuddy.app.controllers;


import com.paymybuddy.app.services.interfaces.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class FriendController {
    private final FriendService friendService;

    @GetMapping(value = "/friends")
    public String showAddFriendPage() {
        return "friends";
    }


    public void AddFriend() {
    }
}
