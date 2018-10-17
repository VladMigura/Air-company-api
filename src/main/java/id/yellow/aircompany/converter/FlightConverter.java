package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.model.FlightModel;

import java.util.ArrayList;
import java.util.List;

public class FlightConverter {

    public static FlightModel toFlightModel(FlightEntity flightEntity) {

        return FlightModel.builder()
                .id(flightEntity.getId())
                .serialNumber(flightEntity.getSerialNumber())
                .dateTime(flightEntity.getDateTime())
                .destinationFrom(flightEntity.getDestinationFrom())
                .destinationTo(flightEntity.getDestinationTo())
                .price(flightEntity.getPrice())
                .numOfSeats(flightEntity.getNumOfSeats())
                .build();
    }

    public static List<FlightModel> toFlightModels(List<FlightEntity> flightEntities) {

        List<FlightModel> flightModels = new ArrayList<>();

        flightEntities.forEach(flightEntity -> {
            flightModels.add(FlightConverter.toFlightModel(flightEntity));
        });

        return flightModels;
    }

    public static FlightEntity toFlightEntity(FlightModel flightModel) {

        return FlightEntity.builder()
                .serialNumber(flightModel.getSerialNumber())
                .dateTime(flightModel.getDateTime())
                .destinationFrom(flightModel.getDestinationTo())
                .destinationTo(flightModel.getDestinationFrom())
                .price(flightModel.getPrice())
                .numOfSeats(flightModel.getNumOfSeats())
                .build();
    }

    public static List<FlightEntity> toFlightEntities(List<FlightModel> flightModels) {

        List<FlightEntity> flightEntities = new ArrayList<>();

        flightModels.forEach(flightModel -> {
            flightEntities.add(FlightConverter.toFlightEntity(flightModel));
        });

        return flightEntities;
    }
}
