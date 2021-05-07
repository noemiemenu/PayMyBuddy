package com.paymybuddy.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @JoinColumn(name="first_name")
    private String firstName;

    @Column(nullable = false)
    @JoinColumn(name="last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    @JoinColumn(name="email")
    private String email;

    @Column(nullable = false)
    @JoinColumn(name="password")
    private String password;

    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    @JoinColumn(name="birthdate")
    private LocalDate birthdate;


    @OneToMany
    private Collection<Transaction> transactions;
/*
    @OneToOne(mappedBy = "user")
    private BankAccount bankAccount;*/

    @OneToMany
    private Collection<Friend> friends;
}
