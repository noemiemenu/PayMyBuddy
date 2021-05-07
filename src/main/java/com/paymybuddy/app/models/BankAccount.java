package com.paymybuddy.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class BankAccount {

    @Id
    @GeneratedValue
    private int id;

    private String rib;
    private double balance;

    @OneToMany
    private Collection<Transaction> transactions;

    @OneToOne
    private User user;
}
