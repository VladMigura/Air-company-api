package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightDiscountModel;

import java.time.LocalDate;
import java.util.List;

public interface FlightDiscountService {

    List<FlightDiscountModel> getFlightDiscounts(int page, int pageSize, Integer valueTo);
    FlightDiscountModel getFlightDiscount(long id);
    List<Long> createFlightDiscount(int value, LocalDate dateFrom, LocalDate dateTo, List<Long> flightIds);
    void deleteFlightDiscount(long id);
}
