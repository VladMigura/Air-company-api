package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.model.FlightModelForCreating;
import id.yellow.aircompany.model.FlightModelForUser;

import java.util.ArrayList;
import java.util.List;

public class FlightConverter {

    public static FlightModelForCreating toFlightModelForCreating(FlightEntity flightEntity) {

        return FlightModelForCreating.builder()
                .id(flightEntity.getId())
                .serialNumber(flightEntity.getSerialNumber())
                .dateTime(flightEntity.getDateTime())
                .destinationFrom(flightEntity.getDestinationFrom())
                .destinationTo(flightEntity.getDestinationTo())
                .price(flightEntity.getPrice())
                .numOfSeats(flightEntity.getNumOfSeats())
                .build();
    }

    public static FlightModelForUser toFlightModelForUser(FlightEntity flightEntity) {

        return FlightModelForUser.builder()
                .id(flightEntity.getId())
                .serialNumber(flightEntity.getSerialNumber())
                .dateTime(flightEntity.getDateTime())
                .destinationFrom(flightEntity.getDestinationFrom())
                .destinationTo(flightEntity.getDestinationTo())
                .price(flightEntity.getPrice())
                .numOfSeats(flightEntity.getNumOfSeats())
                .build();
    }

    public static List<FlightModelForCreating> toFlightModelsForCreating(List<FlightEntity> flightEntities) {

        List<FlightModelForCreating> flightModelsForCreating = new ArrayList<>();

        flightEntities.forEach(flightEntity -> {
            flightModelsForCreating.add(FlightConverter.toFlightModelForCreating(flightEntity));
        });

        return flightModelsForCreating;
    }

    public static List<FlightModelForUser> toFlightModelsForUser(List<FlightEntity> flightEntities) {

        List<FlightModelForUser> flightModelsForUser = new ArrayList<>();

        flightEntities.forEach(flightEntity -> {
            flightModelsForUser.add(FlightConverter.toFlightModelForUser(flightEntity));
        });

        return flightModelsForUser;
    }

    public static FlightEntity toFlightEntity(FlightModelForCreating flightModelForCreating) {

        return FlightEntity.builder()
                .serialNumber(flightModelForCreating.getSerialNumber())
                .dateTime(flightModelForCreating.getDateTime())
                .destinationFrom(flightModelForCreating.getDestinationTo())
                .destinationTo(flightModelForCreating.getDestinationFrom())
                .price(flightModelForCreating.getPrice())
                .numOfSeats(flightModelForCreating.getNumOfSeats())
                .build();
    }

    public static List<FlightEntity> toFlightEntities(List<FlightModelForCreating> flightModelForCreatings) {

        List<FlightEntity> flightEntities = new ArrayList<>();

        flightModelForCreatings.forEach(flightModelForCreating -> {
            flightEntities.add(FlightConverter.toFlightEntity(flightModelForCreating));
        });

        return flightEntities;
    }
}
