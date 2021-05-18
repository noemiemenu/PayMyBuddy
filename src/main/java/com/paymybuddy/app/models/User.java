package com.paymybuddy.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "sender")
    private Collection<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver")
    private Collection<Transaction> receiveTransactions;

    @OneToOne(mappedBy = "user")
    private InternalBankAccount internalBankAccount;

    @OneToMany(mappedBy = "user")
    private Collection<ExternalBankAccount> externalBankAccounts;

    @OneToMany(mappedBy = "friendUser")
    private Collection<Friend> friends;
}
