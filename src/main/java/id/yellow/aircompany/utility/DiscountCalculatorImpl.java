package id.yellow.aircompany.utility;

import id.yellow.aircompany.entity.FlightDiscountEntity;
import id.yellow.aircompany.entity.UserDiscountEntity;
import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.repository.FlightDiscountRepository;
import id.yellow.aircompany.repository.UserDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class DiscountCalculatorImpl implements DiscountCalculator {

    @Autowired
    private FlightDiscountRepository flightDiscountRepository;

    @Autowired
    private UserDiscountRepository userDiscountRepository;

    @Override
    public BigDecimal calculateTicketPrice(long flightId, BigDecimal flightPrice) {

        double userDiscountValue = getUserDiscountValue();
        double flightDiscountValue = getFlightDiscountValue(flightId);

        double totalDiscount = (100 - flightDiscountValue) * userDiscountValue / 100 + flightDiscountValue;

        return BigDecimal.valueOf(flightPrice.doubleValue() * (100 - totalDiscount) / 100)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public FlightModelForUser calculateDiscount(FlightModelForUser flightModelForUser) {

        double userDiscountValue = getUserDiscountValue();
        double flightDiscountValue = getFlightDiscountValue(flightModelForUser.getId());

        double totalDiscount = (100 - flightDiscountValue) * userDiscountValue / 100 + flightDiscountValue;

        flightModelForUser.setPriceWithDiscount(BigDecimal
                .valueOf(flightModelForUser.getPrice().doubleValue() * (100 - totalDiscount) / 100)
                .setScale(2, RoundingMode.HALF_UP));

        return flightModelForUser;
    }

    @Override
    public List<FlightModelForUser> calculateDiscounts(List<FlightModelForUser> flightModelsForUser) {

        double userDiscountValue = getUserDiscountValue();

        for(FlightModelForUser flightModelForUser: flightModelsForUser) {
            double flightDiscountValue = getFlightDiscountValue(flightModelForUser.getId());

            double totalDiscount = (100 - flightDiscountValue) * userDiscountValue / 100 + flightDiscountValue;

            flightModelForUser.setPriceWithDiscount(BigDecimal
                    .valueOf(flightModelForUser.getPrice().doubleValue() * (100 - totalDiscount) / 100)
                    .setScale(2, RoundingMode.HALF_UP));
        }

        return flightModelsForUser;
    }

    private double getUserDiscountValue() {

        long userId = SecurityUtility.getUserId();
        double userDiscountValue = 0;

        List<UserDiscountEntity> userDiscountEntities = userDiscountRepository.findActualAllByUserId(userId);

        for(UserDiscountEntity userDiscountEntity: userDiscountEntities) {
            userDiscountValue = (100 - userDiscountValue) *
                    userDiscountEntity.getValue().doubleValue() / 100 +
                    userDiscountValue;
        }

        return userDiscountValue;
    }

    private double getFlightDiscountValue(long flightId) {

        List<FlightDiscountEntity> flightDiscountEntities =
                flightDiscountRepository.findActualAllByFlightId(flightId);
        double flightDiscountValue = 0;

        for(FlightDiscountEntity flightDiscountEntity: flightDiscountEntities) {
            flightDiscountValue = (100 - flightDiscountValue) *
                    flightDiscountEntity.getValue().doubleValue() / 100 +
                    flightDiscountValue;
        }

        return flightDiscountValue;
    }
}
