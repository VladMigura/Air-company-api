package id.yellow.aircompany.utility;

import id.yellow.aircompany.model.FlightModelForUser;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountCalculator {

    FlightModelForUser calculateDiscount(FlightModelForUser flightModelForUser);
    List<FlightModelForUser> calculateDiscounts(List<FlightModelForUser> flightModelsForUser);
    BigDecimal calculateTicketPrice(long flightId, BigDecimal flightPrice);
}
