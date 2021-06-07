package com.paymybuddy.app.services;

import com.paymybuddy.app.exceptions.FriendAlreadyLinkedException;
import com.paymybuddy.app.exceptions.UserNotFoundException;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.models.Friend;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.FriendsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Friend service.
 */
@AllArgsConstructor
@Service
public class FriendServiceImpl implements FriendService {
    private final FriendsRepository friendsRepository;
    private final UsersRepository usersRepository;

    @Override
    public void addFriend(AddFriendForm addFriendForm, User currentUser) throws UserNotFoundException, FriendAlreadyLinkedException {
        User newFriend = usersRepository.findUserByEmail(addFriendForm.getFriendEmail());

        if (newFriend == null){
            throw new UserNotFoundException("User not found with email: " + addFriendForm.getFriendEmail());
        }

        if (currentUser.getFriends().stream().anyMatch(friend -> friend.getFriendUser().getId() == newFriend.getId())){
            throw new FriendAlreadyLinkedException("Friend already linked with email: " + addFriendForm.getFriendEmail());
        }

        Friend localRelation = new Friend();
        localRelation.setUser(currentUser);
        localRelation.setFriendUser(newFriend);
        friendsRepository.save(localRelation);
    }
}
