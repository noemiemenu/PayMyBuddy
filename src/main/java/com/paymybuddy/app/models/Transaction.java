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
public class Transaction {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private BankAccount bankAccount;

    private LocalDateTime Date;
    private double amount;
    private String wording;
    private TransactionType type;

    @OneToOne
    private User receiver;

    @OneToOne
    private User sender;

}
