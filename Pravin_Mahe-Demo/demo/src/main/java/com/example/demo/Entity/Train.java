package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Train {

    @Id
    @GeneratedValue
    private Long id;
    private String section;

    @OneToMany(mappedBy = "train")
    private List<Ticket> tickets; // Changed from User to Ticket

    // getters and setters
}
