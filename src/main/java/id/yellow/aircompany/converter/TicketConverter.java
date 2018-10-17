package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.entity.TicketEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.model.TicketModel;

import java.util.ArrayList;
import java.util.List;

public class TicketConverter {

    public static TicketModel toTicketModel(TicketEntity ticketEntity) {

        return TicketModel.builder()
                .id(ticketEntity.getId())
                .serialNumber(ticketEntity.getSerialNumber())
                .flightId(ticketEntity.getFlightEntity().getId())
                .userId(ticketEntity.getUserEntity().getId())
                .place(ticketEntity.getPlace())
                .price(ticketEntity.getPrice())
                .build();
    }

    public static List<TicketModel> toTicketModels(List<TicketEntity> ticketEntities) {

        List<TicketModel> ticketModels = new ArrayList<>();

        ticketEntities.forEach(ticketEntity -> {
            ticketModels.add(TicketConverter.toTicketModel(ticketEntity));
        });

        return ticketModels;
    }

    public static TicketEntity toTicketEntity(TicketModel ticketModel) {

        return TicketEntity.builder()
                .serialNumber(ticketModel.getSerialNumber())
                .flightEntity(FlightEntity.builder().id(ticketModel.getFlightId()).build())
                .userEntity(UserEntity.builder().id(ticketModel.getUserId()).build())
                .place(ticketModel.getPlace())
                .price(ticketModel.getPrice())
                .build();
    }

    public static List<TicketEntity> toTicketEntities(List<TicketModel> ticketModels) {

        List<TicketEntity> ticketEntities = new ArrayList<>();

        ticketModels.forEach(ticketModel -> {
            ticketEntities.add(TicketConverter.toTicketEntity(ticketModel));
        });

        return ticketEntities;
    }
}
