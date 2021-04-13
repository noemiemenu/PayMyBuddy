package com.paymybuddy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private java.time.LocalDate birthdate;

    @OneToOne
    private BankAccount bankAccount;
}