package id.yellow.aircompany.utility;

import id.yellow.aircompany.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUtility {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightDiscountRepository flightDiscountRepository;

    @Autowired
    private UserDiscountRepository userDiscountRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Scheduled(fixedRate = 60000)
    public void cleanDatabase() {

        flightRepository.clearNotActualRecords();
        flightDiscountRepository.clearNotActualRecords();
        userDiscountRepository.clearNotActualRecords();
        ticketRepository.clearNotActualRecords();
    }
}
