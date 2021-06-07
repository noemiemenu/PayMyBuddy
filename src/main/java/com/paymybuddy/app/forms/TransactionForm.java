package com.paymybuddy.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Transaction form.
 */
@AllArgsConstructor
@Getter
@Setter
public class TransactionForm extends AbstractTransactionForm {

    private int externalBankAccountId;

}
