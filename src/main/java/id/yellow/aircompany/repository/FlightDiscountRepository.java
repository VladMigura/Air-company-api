package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.FlightDiscountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FlightDiscountRepository extends JpaRepository<FlightDiscountEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM flight_discount " +
                    "WHERE (:valueTo IS NULL OR flight_discount.value >= :valueFrom) " +
                    "AND deleted_at IS NULL ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM flight_discount " +
                    "WHERE (:valueTo IS NULL OR flight_discount.value >= :valueFrom) " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<FlightDiscountEntity> findAllByParameters(@Param("valueFrom") Integer valueFrom,
                                                   Pageable pageable);

    @Query(value = "SELECT * " +
                    "FROM flight_discount " +
                    "WHERE id = :flightDiscountId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    FlightDiscountEntity findOneById(@Param(value = "flightDiscountId") long flightDiscountId);

    @Query(value = "SELECT * " +
                    "FROM flight_discount " +
                    "WHERE flight_id = :flightId " +
                    "AND to_date > now() " +
                    "AND deleted_at IS NULL " +
                    "ORDER BY from_date ASC ",
            nativeQuery = true)
    List<FlightDiscountEntity> findActualAllByFlightId(@Param("flightId") Long flightId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE flight_discount " +
                    "SET deleted_at = now() " +
                    "WHERE id = :flightDiscountId ",
            nativeQuery = true)
    void deleteOneById(@Param("flightDiscountId") long flightDiscountId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE flight_discount " +
                    "SET deleted_at = now() " +
                    "WHERE now() > to_date ",
            nativeQuery = true)
    void clearNotActualRecords();
}
