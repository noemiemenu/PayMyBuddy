package com.paymybuddy.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private User user;

    @ManyToOne
    private User friendUser;
}
