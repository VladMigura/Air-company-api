package id.yellow.aircompany.model;

import id.yellow.aircompany.entity.FlightDiscountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDiscountModel {

    private BigDecimal value;
    private long flightSerialNumber;
    private Instant fromDate;
    private Instant toDate;

    public static FlightDiscountModel from(FlightDiscountEntity flightDiscountEntity) {

        return FlightDiscountModel.builder()
                .value(flightDiscountEntity.getValue())
                .flightSerialNumber(flightDiscountEntity.getFlightEntity().getSerialNumber())
                .fromDate(flightDiscountEntity.getFromDate())
                .toDate(flightDiscountEntity.getToDate())
                .build();
    }

    public static List<FlightDiscountModel> from(List<FlightDiscountEntity> flightDiscountEntities) {

        List<FlightDiscountModel> flightDiscountModels = new ArrayList<>();

        flightDiscountEntities.forEach(flightDiscountEntity -> {
            flightDiscountModels.add(FlightDiscountModel.from(flightDiscountEntity));
        });

        return flightDiscountModels;
    }
}
