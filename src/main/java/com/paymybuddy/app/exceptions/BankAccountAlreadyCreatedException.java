package com.paymybuddy.app.exceptions;

/**
 * The type Bank account already created exception.
 */
public class BankAccountAlreadyCreatedException extends Exception{
    /**
     * Instantiates a new Bank account already created exception.
     *
     * @param message the message
     */
    public BankAccountAlreadyCreatedException(String message) {
        super(message);
    }
}
