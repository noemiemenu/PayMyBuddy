package com.paymybuddy.app.exceptions;

public class UserAlreadyCreatedException extends Exception{

    public UserAlreadyCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
