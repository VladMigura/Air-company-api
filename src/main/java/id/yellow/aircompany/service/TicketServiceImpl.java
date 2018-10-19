package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.TicketConverter;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.exception.BadRequestException;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.TicketModel;
import id.yellow.aircompany.repository.FlightRepository;
import id.yellow.aircompany.repository.TicketRepository;
import id.yellow.aircompany.utility.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final int MAX_COUNT_OF_TICKETS = 10;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DiscountCalculator discountCalculator;

    @Override
    @PreAuthorize("@securityUtility.isAdmin() or @securityUtility.isOwnerById(#id)")
    public List<TicketModel> getTickets(long id) {

        return TicketConverter.toTicketModels(ticketRepository.findAllByUserEntityId(id));
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin() or @securityUtility.isOwnerById(#id)")
    public TicketModel getTicketById(long id, long ticketId) {

        return TicketConverter.toTicketModel(ticketRepository.findOneById(ticketId));
    }

    @Override
    @PreAuthorize("@securityUtility.isOwnerById(#id)")
    public List<TicketModel> createTickets(long id, long flightId, int count, List<TicketModel> ticketModels) {

        FlightEntity flightEntity = flightRepository.findOneById(flightId);

        if(flightEntity != null) {
            if(count > MAX_COUNT_OF_TICKETS) {
                throw new BadRequestException("Too many tickets!");
            } else if(count > flightEntity.getNumOfSeats()) {
                throw new BadRequestException("Not enough seats! You are in the waiting list.");
            }

            ticketModels.forEach(ticketModel -> {
                ticketModel.setUserId(id);
                ticketModel.setFlightId(flightId);
                ticketModel.setPrice(discountCalculator.calculateTicketPrice(flightId, flightEntity.getPrice()));
            });



            return null;
        }

        throw new NotFoundException("Flight with this id is not found!");
    }
}
