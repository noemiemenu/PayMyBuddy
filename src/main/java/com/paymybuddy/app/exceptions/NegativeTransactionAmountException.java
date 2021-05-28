package com.paymybuddy.app.exceptions;

public class NegativeTransactionAmountException extends Exception {
    public NegativeTransactionAmountException(String message) {
        super(message);
    }
}
