package com.paymybuddy.app.services;

import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.InternalBankAccount;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.InternalBankAccountRepository;
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


@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final InternalBankAccountRepository internalBankAccountRepository;
    private final SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();


    @Override
    public void registerUser(User user) throws UserAlreadyCreatedException {
        User alreadyRegisteredUser = usersRepository.findUserByEmail(user.getEmail());
        if (alreadyRegisteredUser != null) {
            throw new UserAlreadyCreatedException("User already created");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User createdUser = usersRepository.save(user);

        InternalBankAccount internalBankAccount = new InternalBankAccount();
        internalBankAccount.setUser(createdUser);
        internalBankAccountRepository.save(internalBankAccount);
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
