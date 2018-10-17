package id.yellow.aircompany.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket")
public class TicketEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serial_number")
    private long serialNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flightEntity;

    @Column(name = "flight_id", updatable = false, insertable = false)
    private long flightId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;

    @Column(name = "place")
    private String place;

    @Column(name = "price")
    private BigDecimal price;
}
