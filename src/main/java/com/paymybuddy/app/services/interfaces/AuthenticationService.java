package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Authentication service.
 */
public interface AuthenticationService {

    /**
     * Register user.
     *
     * @param user the user
     * @throws UserAlreadyCreatedException the user already created exception
     */
    void registerUser(User user) throws UserAlreadyCreatedException;

    /**
     * Logout.
     *
     * @param request  the request
     * @param response the response
     */
    void logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * Gets current logged user.
     *
     * @param request the request
     * @return the current logged user
     */
    User getCurrentLoggedUser(HttpServletRequest request);
}
