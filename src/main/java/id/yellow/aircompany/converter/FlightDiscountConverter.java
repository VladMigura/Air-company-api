package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.FlightDiscountEntity;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.model.FlightDiscountModel;

import java.util.ArrayList;
import java.util.List;

public class FlightDiscountConverter {

    public static FlightDiscountModel toFlightDiscountModel(FlightDiscountEntity flightDiscountEntity) {

        return FlightDiscountModel.builder()
                .id(flightDiscountEntity.getId())
                .value(flightDiscountEntity.getValue())
                .flightId(flightDiscountEntity.getFlightEntity().getId())
                .fromDate(flightDiscountEntity.getFromDate())
                .toDate(flightDiscountEntity.getToDate())
                .build();
    }

    public static List<FlightDiscountModel> toFlightDiscountModels(List<FlightDiscountEntity> flightDiscountEntities) {

        List<FlightDiscountModel> flightDiscountModels = new ArrayList<>();

        flightDiscountEntities.forEach(flightDiscountEntity -> {
            flightDiscountModels.add(FlightDiscountConverter.toFlightDiscountModel(flightDiscountEntity));
        });

        return flightDiscountModels;
    }

    public static FlightDiscountEntity toFlightDiscountEntity(FlightDiscountModel flightDiscountModel) {

        return FlightDiscountEntity.builder()
                .value(flightDiscountModel.getValue())
                .flightEntity(FlightEntity.builder().id(flightDiscountModel.getFlightId()).build())
                .fromDate(flightDiscountModel.getFromDate())
                .toDate(flightDiscountModel.getToDate())
                .build();
    }

    public static List<FlightDiscountEntity> toflightDiscountEntities(List<FlightDiscountModel> flightDiscountModels) {

        List<FlightDiscountEntity> flightDiscountEntities = new ArrayList<>();

        flightDiscountModels.forEach(flightDiscountModel -> {
            flightDiscountEntities.add(FlightDiscountConverter.toFlightDiscountEntity(flightDiscountModel));
        });

        return flightDiscountEntities;
    }
}
