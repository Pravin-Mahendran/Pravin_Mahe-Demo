package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String from;
    private String to;
    private String seat;

    @Enumerated(EnumType.STRING)
    private Session session;

    public BookTicket(User user, String from, String to, String seat, Session session) {
        this.user = user;
        this.from = from;
        this.to = to;
        this.seat = seat;
        this.session = session;
    }
}
