package com.paymybuddy.models;

import com.paymybuddy.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private int id;
    private int bankAccountId;
    private LocalDateTime Date;
    private double amount;
    private String wording;
    private TransactionType type;
}
