package com.paymybuddy.app.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractTransactionForm {

    protected double amount;
    protected String wording;

}
