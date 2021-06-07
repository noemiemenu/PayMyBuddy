package com.paymybuddy.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * The type Internal bank account.
 */
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class InternalBankAccount {

    @Id
    @GeneratedValue
    private int id;

    private double balance;

    @OneToMany(mappedBy = "internalBankAccount")
    private Collection<Transaction> transactions;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
