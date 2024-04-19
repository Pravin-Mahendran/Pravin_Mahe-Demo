package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String from;
    private String to;
    private String seat;
    private String section;
    private int ticketNumber; // This field represents the ticket number

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    // getters and setters

    @PrePersist
    public void generateTicketNumber() {
        this.ticketNumber = new Random().nextInt(900000) + 100000; // Generate a 6-digit random number
    }
}
