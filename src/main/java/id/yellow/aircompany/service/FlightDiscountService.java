package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightDiscountModel;

import java.util.List;

public interface FlightDiscountService {

    List<FlightDiscountModel> getFlightDiscounts(int page, int pageSize, int valueTo);
    FlightDiscountModel getFlightDiscount(long id);
    void deleteFlightDiscount(long id);
}
