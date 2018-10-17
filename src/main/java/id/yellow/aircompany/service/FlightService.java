package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightModel;

import java.math.BigDecimal;
import java.util.List;

public interface FlightService {

    List<FlightModel> getFlights(int page, int pageSize, String dateFrom, String dateTo,
                                 String destFrom, String destTo, BigDecimal priceFrom, BigDecimal priceTo);

    long createFlight(FlightModel flightModel);
    void deleteFlight(String flightSerialNumber);
}
