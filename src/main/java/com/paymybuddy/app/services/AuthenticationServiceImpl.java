package com.paymybuddy.app.services;

import com.paymybuddy.app.exceptions.LegalAgeException;
import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;


/**
 * The type Authentication service.
 */
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();


    @Override
    public void registerUser(User user) throws UserAlreadyCreatedException, LegalAgeException {
        User alreadyRegisteredUser = usersRepository.findUserByEmail(user.getEmail());
        if (alreadyRegisteredUser != null) {
            throw new UserAlreadyCreatedException("User already created");
        }
        int year = Calendar.getInstance().getWeekYear();
        int userYear = user.getBirthdate().getYear();
        if ((year - userYear) < 18 ){
            throw new LegalAgeException("You have not the legal ages required to register to PayMyBuddy");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        usersRepository.save(user);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            securityContextLogoutHandler.logout(request, response, auth);
        }
    }

    public User getCurrentLoggedUser(HttpServletRequest request) {
        return usersRepository.findUserByEmail(request.getUserPrincipal().getName());
    }
}
