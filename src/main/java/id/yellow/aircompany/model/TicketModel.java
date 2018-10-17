package id.yellow.aircompany.model;

import id.yellow.aircompany.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketModel {

    private long serialNumber;
    private long flightSerialNumber;
    private String username;
    private String place;
    private BigDecimal price;

    public static TicketModel from(TicketEntity ticketEntity) {

        return TicketModel.builder()
                .serialNumber(ticketEntity.getSerialNumber())
                .flightSerialNumber(ticketEntity.getFlightEntity().getSerialNumber())
                .username(ticketEntity.getUserEntity().getUsername())
                .place(ticketEntity.getPlace())
                .price(ticketEntity.getPrice())
                .build();
    }

    public static List<TicketModel> ticketModels(List<TicketEntity> ticketEntities) {

        List<TicketModel> ticketModels = new ArrayList<>();

        ticketEntities.forEach(ticketEntity -> {
            ticketModels.add(TicketModel.from(ticketEntity));
        });

        return ticketModels;
    }
}
