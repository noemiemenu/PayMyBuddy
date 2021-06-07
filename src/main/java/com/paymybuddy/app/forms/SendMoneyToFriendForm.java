package com.paymybuddy.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Send money to friend form.
 */
@AllArgsConstructor
@Getter
@Setter
public class SendMoneyToFriendForm extends AbstractTransactionForm{


    private String friendEmail;
}
