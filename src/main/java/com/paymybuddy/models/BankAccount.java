package com.paymybuddy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    @OneToOne
    private User user;
}
