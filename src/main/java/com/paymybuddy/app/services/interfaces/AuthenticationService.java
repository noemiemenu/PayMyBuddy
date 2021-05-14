package com.paymybuddy.app.services.interfaces;

import com.paymybuddy.app.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    void registerUser(User user);

    void logIn();

    void logout(HttpServletRequest request, HttpServletResponse response);

}
