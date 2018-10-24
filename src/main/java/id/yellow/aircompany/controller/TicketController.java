package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.TicketModel;
import id.yellow.aircompany.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public List<TicketModel> getTickets(@RequestParam(name = "userId", required = false) Long userId) {

        return ticketService.getTickets(userId);
    }

    @GetMapping("/tickets/{ticketId}")
    public TicketModel getTicket(@PathVariable long ticketId) {

        return ticketService.getTicketById(ticketId);
    }

    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TicketModel> createTickets(@RequestParam(required = false, defaultValue = "1") int count,
                                           @RequestBody List<TicketModel> ticketModels) {

        return ticketService.createTickets(ticketModels, count);
    }
}
