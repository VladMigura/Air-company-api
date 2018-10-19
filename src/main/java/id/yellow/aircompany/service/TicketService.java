package id.yellow.aircompany.service;

import id.yellow.aircompany.model.TicketModel;

import java.util.List;

public interface TicketService {

    List<TicketModel> getTickets(long id);
    TicketModel getTicketById(long id, long ticketId);
    List<TicketModel> createTickets(long id, long flightId, int count, List<TicketModel> ticketModels);
}
