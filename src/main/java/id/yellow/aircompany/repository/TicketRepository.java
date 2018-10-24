package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM ticket " +
                    "WHERE id = :ticketId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    TicketEntity findOneById(@Param(value = "ticketId") long ticketId);

    @Query(value = "SELECT * " +
                    "FROM ticket " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    List<TicketEntity> findAllByUserId(@Param(value = "userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ticket " +
            "SET deleted_at = now() " +
            "WHERE flight_id IN (" +
            "SELECT id " +
            "FROM flight " +
            "WHERE flight.deleted_at NOTNULL) ",
            nativeQuery = true)
    void clearNotActualRecords();
}
