package com.paymybuddy.app;

import com.paymybuddy.app.exceptions.FriendAlreadyLinkedException;
import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.exceptions.UserNotFoundException;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.models.Friend;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.FriendsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class FriendTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationService authenticationService;

    private User user;

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendsRepository friendsRepository;

    @BeforeEach
    public void setupTest() throws UserAlreadyCreatedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2019", formatter));

        authenticationService.registerUser(user);
        this.user = usersRepository.findUserByEmail(user.getEmail());
        }

    @Test
    public void addFriendTest(){
        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var friendRef = new Object() {
            User friend = new User();
        };
        friendRef.friend.setFirstName("noemie");
        friendRef.friend.setLastName("menu");
        friendRef.friend.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        friendRef.friend.setPassword("nm2021");
        friendRef.friend.setBirthdate(LocalDate.parse("29-04-2019", formatter));
        assertDoesNotThrow(() -> authenticationService.registerUser(friendRef.friend));
        friendRef.friend = usersRepository.findUserByEmail(friendRef.friend.getEmail());
        AddFriendForm addFriendForm = new AddFriendForm(friendRef.friend.getEmail());

        //when
        assertDoesNotThrow(() -> friendService.addFriend(addFriendForm, user));

        //then
        user.setFriends(friendsRepository.findFriendsByUserId(user.getId()));
        Optional<Friend> friend1 = user.getFriends().stream().filter((friend) -> friend.getFriendUser().getId() == friendRef.friend.getId()).findAny();
        assertTrue(friend1.isPresent());
    }

    @Test
    public void addFriend_must_throw_UserNotFoundException(){
       //given
       AddFriendForm addFriendForm = new AddFriendForm(" ");

       //when & then
        assertThrows(UserNotFoundException.class,() ->friendService.addFriend(addFriendForm, user));
    }

    @Test
    public void addFriend_must_throw_FriendAlreadyLinkedException(){
        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var friendRef = new Object() {
            User friend = new User();
        };
        friendRef.friend.setFirstName("noemie");
        friendRef.friend.setLastName("menu");
        friendRef.friend.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        friendRef.friend.setPassword("nm2021");
        friendRef.friend.setBirthdate(LocalDate.parse("29-04-2019", formatter));
        assertDoesNotThrow(() -> authenticationService.registerUser(friendRef.friend));
        friendRef.friend = usersRepository.findUserByEmail(friendRef.friend.getEmail());
        AddFriendForm addFriendForm = new AddFriendForm(friendRef.friend.getEmail());
        assertDoesNotThrow(() -> friendService.addFriend(addFriendForm, user));

        //when
        user.setFriends(friendsRepository.findFriendsByUserId(user.getId()));
        assertThrows(FriendAlreadyLinkedException.class,() ->friendService.addFriend(addFriendForm, user));
        //then
    }

}
