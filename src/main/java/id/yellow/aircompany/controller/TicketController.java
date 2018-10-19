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
    public List<TicketModel> getTickets(@RequestParam(name = "userId") long userId) {

        return ticketService.getTickets(userId);
    }

    @GetMapping("/tickets/{ticketId}")
    public TicketModel getTicket(@RequestParam(name = "userId") long userId,
                                 @PathVariable long ticketId) {

        return ticketService.getTicketById(userId, ticketId);
    }

    @PostMapping("/users/{id}/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TicketModel> createTickets(@PathVariable long id,
                                    @RequestParam long flightId,
                                    @RequestParam(required = false, defaultValue = "1") int count,
                                    @RequestBody List<TicketModel> ticketModels) {

        return ticketService.createTickets(id, flightId, count, ticketModels);
    }
}
