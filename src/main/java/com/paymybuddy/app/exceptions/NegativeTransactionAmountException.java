package com.paymybuddy.app.exceptions;

/**
 * The type Negative transaction amount exception.
 */
public class NegativeTransactionAmountException extends Exception {
    /**
     * Instantiates a new Negative transaction amount exception.
     *
     * @param message the message
     */
    public NegativeTransactionAmountException(String message) {
        super(message);
    }
}
