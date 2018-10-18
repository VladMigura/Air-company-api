package id.yellow.aircompany.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightModelForCreating {

    private long id;
    private long serialNumber;
    private Instant dateTime;
    private String destinationFrom;
    private String destinationTo;
    private BigDecimal price;
    private int numOfSeats;
}
