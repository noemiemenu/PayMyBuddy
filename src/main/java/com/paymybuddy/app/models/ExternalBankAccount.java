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
@Table(name = "external_bank_accounts")
public class ExternalBankAccount {

    @Id
    @GeneratedValue
    private int id;

    private String rib;

    private String name;

    @OneToMany
    private Collection<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}