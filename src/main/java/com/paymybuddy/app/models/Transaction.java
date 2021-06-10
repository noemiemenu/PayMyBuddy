package com.paymybuddy.app.models;

import com.paymybuddy.app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Transaction.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private int id;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime Date;

    private double amount;

    private String wording;

    private TransactionType type;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private User sender;


    /**
     * Format transaction type string.
     *
     * @param currentUserId the current user id
     * @return the string
     */
    public String formatTransactionType(int currentUserId) {
        switch (type) {
            case ADD_TO_INTERNAL_ACCOUNT:
                return "Add to PayMyBuddy account from external bank account: ";
            case SEND_TO_BANK:
                return "Sent to bank: ";
            case SEND_TO_FRIEND:
                if (currentUserId == receiver.getId()) {
                    return "Receive from: " + sender.getFullName();
                }

                return "Sent to: " + receiver.getFullName();
        }
        return null;
    }

    /**
     * Get sender full name string.
     *
     * @return the string
     */
    public String getSenderFullName(){
        if (sender == null){
            return "";
        }
        return sender.getFullName();
    }

    /**
     * Get receiver full name string.
     *
     * @return the string
     */
    public String getReceiverFullName(){
        if (receiver == null){
            return "";
        }
        return receiver.getFullName();
    }
}
