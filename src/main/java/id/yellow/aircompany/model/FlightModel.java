package id.yellow.aircompany.model;

import id.yellow.aircompany.entity.FlightEntity;
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
public class FlightModel {

    private long serialNumber;
    private Instant dateTime;
    private String destinationFrom;
    private String destinationTo;
    private BigDecimal price;
    private int numOfSeats;

    public static FlightModel from(FlightEntity flightEntity) {

        return FlightModel.builder()
                .serialNumber(flightEntity.getSerialNumber())
                .dateTime(flightEntity.getDateTime())
                .destinationFrom(flightEntity.getDestinationFrom())
                .destinationTo(flightEntity.getDestinationTo())
                .price(flightEntity.getPrice())
                .numOfSeats(flightEntity.getNumOfSeats())
                .build();
    }

    public static List<FlightModel> from(List<FlightEntity> flightEntities) {

        List<FlightModel> flightModels = new ArrayList<>();

        flightEntities.forEach(flightEntity -> {
            flightModels.add(FlightModel.from(flightEntity));
        });

        return flightModels;
    }
}
