package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.TicketConverter;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.entity.TicketEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.entity.WaitingRecordEntity;
import id.yellow.aircompany.exception.BadRequestException;
import id.yellow.aircompany.exception.ForbiddenException;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.TicketModel;
import id.yellow.aircompany.repository.FlightRepository;
import id.yellow.aircompany.repository.TicketRepository;
import id.yellow.aircompany.repository.WaitingListRepository;
import id.yellow.aircompany.utility.DiscountCalculator;
import id.yellow.aircompany.utility.SecurityUtility;
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
    private WaitingListRepository waitingListRepository;

    @Autowired
    private DiscountCalculator discountCalculator;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<TicketModel> getTickets(Long userId) {

        if(SecurityUtility.isAdmin()) {
            if(userId != null) {
                return TicketConverter.toTicketModels(ticketRepository.findAllByUserId(userId));
            } else {
                throw new BadRequestException("Parameter 'userId' is missing!");
            }
        }

        userId = SecurityUtility.getUserId();

        return TicketConverter.toTicketModels(ticketRepository.findAllByUserId(userId));
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public TicketModel getTicketById(long ticketId) {

        TicketEntity ticketEntity = ticketRepository.findOneById(ticketId);

        if(ticketEntity != null && (SecurityUtility.isAdmin() ||
                ticketEntity.getUserId() == SecurityUtility.getUserId())) {

            return TicketConverter.toTicketModel(ticketEntity);
        } else {
            throw new NotFoundException("Ticket is not found!");
        }
    }

    @Override
    @PreAuthorize("@securityUtility.isUser()")
    public List<TicketModel> createTickets(List<TicketModel> ticketModels, int count) {

        long userId = SecurityUtility.getUserId();
        long flightId = ticketModels.get(0).getFlightId();
        FlightEntity flightEntity = flightRepository.findOneById(flightId);

        if(flightEntity != null) {
            if(count > MAX_COUNT_OF_TICKETS) {
                throw new BadRequestException("Too many tickets!");
            } else if(count > flightEntity.getNumOfSeats()) {
                WaitingRecordEntity waitingRecordEntity = WaitingRecordEntity.builder()
                        .userEntity(UserEntity.builder().id(userId).build())
                        .destinationTo(flightEntity.getDestinationTo())
                        .build();

                waitingListRepository.save(waitingRecordEntity);

                throw new BadRequestException("Not enough seats! You are in the waiting list.");
            }

            ticketModels.forEach(ticketModel -> {
                ticketModel.setUserId(userId);
                ticketModel.setPrice(discountCalculator.calculateTicketPrice(flightId, flightEntity.getPrice()));
            });

            flightEntity.setNumOfSeats(flightEntity.getNumOfSeats() - count);
            flightRepository.save(flightEntity);

            return TicketConverter.toTicketModels(ticketRepository
                    .saveAll(TicketConverter.toTicketEntities(ticketModels)));
        }

        throw new NotFoundException("Flight with this id is not found!");
    }
}
