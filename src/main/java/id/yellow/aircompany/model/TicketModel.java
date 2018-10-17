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

    private long id;
    private long serialNumber;
    private long flightId;
    private long userId;
    private String place;
    private BigDecimal price;
}
