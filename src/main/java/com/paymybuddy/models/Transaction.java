package com.paymybuddy.models;

import com.paymybuddy.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    private int bankAccountId;
    private LocalDateTime Date;
    private double amount;
    private String wording;
    private TransactionType type;
}
