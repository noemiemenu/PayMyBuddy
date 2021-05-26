package com.paymybuddy.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionForm {

    private int externalBankAccountId;
    private double amount;
    private String wording;
}
