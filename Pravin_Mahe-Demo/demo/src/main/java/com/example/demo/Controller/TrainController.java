package com.example.demo.Controller;

import com.example.demo.Entity.BookTicket;
import com.example.demo.Entity.Ticket;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Session;
import com.example.demo.Service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/book")
    public Ticket purchaseTicket(@RequestBody BookTicket bookRequest) {
        User user = bookRequest.getUser();
        String from = bookRequest.getFrom();
        String to = bookRequest.getTo();
        String seat = bookRequest.getSeat();
        Session session = bookRequest.getSession(); // use the Session enum here
        return trainService.purchaseTicket(user, from, to, seat, session);
    }

    @GetMapping("/receipt")
    public Ticket getReceipt(@RequestBody User user) {
        return trainService.getReceipt(user);
    }

    @GetMapping("/users")
    public List<User> getUsersBySection(@RequestParam String section) {
        return trainService.getUsersBySection(section);
    }

    @DeleteMapping("/remove")
    public void removeUser(@RequestBody User user) {
        trainService.removeUser(user);
    }

    @PutMapping("/modify")
    public void modifySeat(@RequestBody User user, @RequestParam String newSeat) {
        trainService.modifySeat(user, newSeat);
    }
}
