package com.paymybuddy.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendMoneyToFriendForm extends AbstractTransactionForm{


    private String friendEmail;
}
