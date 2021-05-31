package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.FriendAlreadyLinkedException;
import com.paymybuddy.app.exceptions.UserNotFoundException;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.models.User;

public interface FriendService {

    void addFriend(AddFriendForm addFriendForm, User user) throws UserNotFoundException, FriendAlreadyLinkedException;
}
