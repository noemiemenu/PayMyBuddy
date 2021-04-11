package com.paymybuddy.models;

public class Transaction {
    private int id;
    private int bankAccountId;
    private java.time.LocalDateTime Date;
    private double amount;
    private String wording;
    private com.paymybuddy.enums.TransactionType type;
}
