package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightModelForCreating;
import id.yellow.aircompany.model.FlightModelForUser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    List<FlightModelForUser> getFlights(int page, int pageSize, LocalDate dateFrom, LocalDate dateTo,
                                        String destFrom, String destTo, double priceFrom, double priceTo,
                                        String sortByDate, String sortByPrice);

    FlightModelForUser getFlightById(long flightId);
    FlightModelForCreating createFlight(FlightModelForCreating flightModelForCreating);
    FlightModelForCreating updateFlight(long flightId, FlightModelForCreating flightModelForCreating);
    void deleteFlight(long flightId);
}
