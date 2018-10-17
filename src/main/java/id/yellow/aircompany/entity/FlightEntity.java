package id.yellow.aircompany.entity;

import id.yellow.aircompany.model.FlightModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "flights")
public class FlightEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serial_number")
    private long serialNumber;

    @Column(name = "date_time")
    private Instant dateTime;

    @Column(name = "destination_from")
    private String destinationFrom;

    @Column(name = "destination_to")
    private String destinationTo;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "num_of_seats")
    private int numOfSeats;

    @OneToMany(mappedBy = "flightEntity")
    private List<FlightDiscountEntity> flightDiscountEntities;

    @OneToMany(mappedBy = "flightEntity")
    private List<TicketEntity> ticketEntities;
}
