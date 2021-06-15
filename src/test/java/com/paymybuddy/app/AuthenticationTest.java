package com.paymybuddy.app;

import com.paymybuddy.app.exceptions.LegalAgeException;
import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class AuthenticationTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void testRegisterUser() throws Exception {
      //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2000", formatter));

        //when
        authenticationService.registerUser(user);

        //Then
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
        user.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2000", formatter));

        authenticationService.registerUser(user);

        assertThrows(UserAlreadyCreatedException.class, () -> authenticationService.registerUser(user));
    }

    @Test
    public void testLegalAgesException() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -17);

        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(calendar.getTime().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate());

        assertThrows(LegalAgeException.class, () -> authenticationService.registerUser(user));
    }


}
