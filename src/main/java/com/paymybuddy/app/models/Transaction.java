package com.paymybuddy.app.models;

import com.paymybuddy.app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
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

}
