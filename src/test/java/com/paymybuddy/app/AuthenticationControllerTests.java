package com.paymybuddy.app;

import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class AuthenticationControllerTests {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void testRegisterUser() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail("nm@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2019", formatter));

        authenticationService.registerUser(user);
        User savedUser = usersRepository.findUserByEmail(user.getEmail());

        assertNotNull(savedUser);
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testAlreadyExistingAccount() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail("nm@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2019", formatter));

        authenticationService.registerUser(user);

        assertThrows(UserAlreadyCreatedException.class, () -> authenticationService.registerUser(user));
    }
}
