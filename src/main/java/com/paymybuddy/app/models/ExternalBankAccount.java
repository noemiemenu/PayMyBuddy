package com.paymybuddy.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * The type External bank account.
 */
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "external_bank_account")
public class ExternalBankAccount {

    @Id
    @GeneratedValue
    private int id;

    private String rib;

    private String name;

    @OneToMany(mappedBy = "externalBankAccount")
    private Collection<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
