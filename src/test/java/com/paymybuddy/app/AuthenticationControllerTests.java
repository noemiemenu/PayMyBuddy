package com.paymybuddy.app;

import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AuthenticationControllerTests {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUser(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail("nm@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2019", formatter));

        User savedUser = usersRepository.save(user);
        User userExist = testEntityManager.find(User.class, savedUser.getId());

        assertThat(userExist.getEmail()).isEqualTo(user.getEmail());

    }
    @Test
    public void testFindUserByEmail(){
        String email = "nm@gmail.com";
        User user = usersRepository.findUserByEmail(email);
        assertThat(user).isNotNull();
    }

}
