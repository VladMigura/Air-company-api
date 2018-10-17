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
public class FlightDiscountModel {

    private long id;
    private BigDecimal value;
    private long flightId;
    private Instant fromDate;
    private Instant toDate;
}
