package com.paymybuddy.app.exceptions;

/**
 * The type User not found exception.
 */
public class UserNotFoundException extends Exception {

    /**
     * Instantiates a new User not found exception.
     *
     * @param errorMessage the error message
     */
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
