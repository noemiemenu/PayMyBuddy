package com.paymybuddy.app.exceptions;

public class BankAccountAlreadyCreatedException extends Exception{
    public BankAccountAlreadyCreatedException(String message) {
        super(message);
    }
}
