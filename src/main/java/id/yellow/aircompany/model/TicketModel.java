package id.yellow.aircompany.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
