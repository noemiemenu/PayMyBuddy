package com.paymybuddy.app.exceptions;

/**
 * The type Friend already linked exception.
 */
public class FriendAlreadyLinkedException extends Exception{
    /**
     * Instantiates a new Friend already linked exception.
     *
     * @param errorMessage the error message
     */
    public FriendAlreadyLinkedException(String errorMessage) {
        super(errorMessage);
    }
}
