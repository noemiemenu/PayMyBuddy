package com.paymybuddy.app.exceptions;

/**
 * The type User already created exception.
 */
public class UserAlreadyCreatedException extends Exception {

    /**
     * Instantiates a new User already created exception.
     *
     * @param errorMessage the error message
     */
    public UserAlreadyCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
