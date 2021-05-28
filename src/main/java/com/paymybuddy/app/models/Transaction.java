package com.paymybuddy.app.models;

import com.paymybuddy.app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private InternalBankAccount internalBankAccount;

    @ManyToOne
    private ExternalBankAccount externalBankAccount;

    private LocalDateTime Date;
    private double amount;
    private String wording;
    private TransactionType type;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private User sender;


    public String formatTransactionType() {
        switch (type) {
            case ADD_TO_INTERNAL_ACCOUNT:
                return "Add to paymybuddy account from external bank account: " + externalBankAccount.getName();
            case SEND_TO_BANK:
                return "Sent to bank: " + externalBankAccount.getName();
            case SEND_TO_FRIEND:
                return "Sent to: " + receiver.getFullName();
        }
        return null;
    }
}
