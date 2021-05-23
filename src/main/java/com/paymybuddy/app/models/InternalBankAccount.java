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
public class  InternalBankAccount {

    @Id
    @GeneratedValue
    private int id;

    private long balance;

    @OneToMany
    private Collection<Transaction> transactions;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
