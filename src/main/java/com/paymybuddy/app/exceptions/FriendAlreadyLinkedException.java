package com.paymybuddy.app.exceptions;

public class FriendAlreadyLinkedException extends Exception{
    public FriendAlreadyLinkedException(String errorMessage) {
        super(errorMessage);
    }
}
