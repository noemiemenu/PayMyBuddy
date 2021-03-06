package com.paymybuddy.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type New external bank account form.
 */
@AllArgsConstructor
@Getter
@Setter
public class AddMoneyToBalanceForm extends AbstractTransactionForm{

    private String bankAccountName;
    private String rib;


}
