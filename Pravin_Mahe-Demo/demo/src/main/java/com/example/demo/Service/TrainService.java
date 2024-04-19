package com.example.demo.Service;

import com.example.demo.Entity.BookTicket;
import com.example.demo.Entity.Ticket;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class TrainService {

    private List<User> users = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public Ticket purchaseTicket(User user, String from, String to, String seat, Session session) {
        // Validate the seat number based on the session
        if ((session == Session.A && (Integer.parseInt(seat.substring(1)) < 1 || Integer.parseInt(seat.substring(1)) > 99)) ||
                (session == Session.B && (Integer.parseInt(seat.substring(1)) < 1 || Integer.parseInt(seat.substring(1)) > 99))) {
            throw new IllegalArgumentException("Invalid seat number for session " + session);
        }

        // Check if the seat is already booked
        if (tickets.stream().anyMatch(ticket -> ticket.getSeat().equals(seat) && ticket.getSection().equals(session.toString()))) {
            throw new IllegalArgumentException("Seat " + seat + " in session " + session + " is already booked");
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setFrom(from);
        ticket.setTo(to);
        ticket.setSeat(seat);
        ticket.setSection(session.toString()); // Set the session

        // Add the entities to the lists
        users.add(user);
        tickets.add(ticket);

        return ticket;
    }

    public Ticket getReceipt(User user) {
        return tickets.stream()
                .filter(ticket -> ticket.getUser().equals(user))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsersBySection(String section) {
        return users.stream()
                .filter(user -> user.getTickets().stream()
                        .anyMatch(ticket -> ticket.getSeat().startsWith(section)))
                .collect(Collectors.toList());
    }

    public void removeUser(User user) {
        users.remove(user);
        tickets.removeIf(ticket -> ticket.getUser().equals(user));
    }

    public void modifySeat(User user, String newSeat) {
        tickets.stream()
                .filter(ticket -> ticket.getUser().equals(user))
                .forEach(ticket -> ticket.setSeat(newSeat));
    }
}
