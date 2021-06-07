package com.paymybuddy.app.forms;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Abstract transaction form.
 */
@Getter
@Setter
public abstract class AbstractTransactionForm {

    /**
     * The Amount.
     */
    protected double amount;
    /**
     * The Wording.
     */
    protected String wording;

}
