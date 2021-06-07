package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.FriendAlreadyLinkedException;
import com.paymybuddy.app.exceptions.UserNotFoundException;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.models.User;

/**
 * The interface Friend service.
 */
public interface FriendService {

    /**
     * Add friend.
     *
     * @param addFriendForm the add friend form
     * @param user          the user
     * @throws UserNotFoundException        the user not found exception
     * @throws FriendAlreadyLinkedException the friend already linked exception
     */
    void addFriend(AddFriendForm addFriendForm, User user) throws UserNotFoundException, FriendAlreadyLinkedException;
}
