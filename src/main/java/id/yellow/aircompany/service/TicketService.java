package id.yellow.aircompany.service;

import id.yellow.aircompany.model.TicketModel;

import java.util.List;

public interface TicketService {

    List<TicketModel> getTickets(Long userId);
    TicketModel getTicketById(long ticketId);
    List<TicketModel> createTickets(List<TicketModel> ticketModels, int count);
}
