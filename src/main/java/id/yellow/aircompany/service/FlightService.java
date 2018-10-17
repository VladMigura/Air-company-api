package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    List<FlightModel> getFlights(int page, int pageSize, LocalDate dateFrom, LocalDate dateTo,
                                 String destFrom, String destTo, BigDecimal priceFrom, BigDecimal priceTo,
                                 String sortByDate, String sortByPrice);

    FlightModel getFlightById(long id);
    FlightModel createFlight(FlightModel flightModel);
    FlightModel updateFlight(long id, FlightModel flightModel);
    void deleteFlight(long id);
}
