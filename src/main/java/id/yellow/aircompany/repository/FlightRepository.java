package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM flight " +
                    "WHERE (:dateFromString IS NULL OR date_time >= to_timestamp(CAST(:dateFromString AS TEXT), " +
                    "'yyyy-mm-ddThh24-mi-ss.maZ')) " +
                    "AND (:dateToString IS NULL OR date_time <= to_timestamp(CAST(:dateToString AS TEXT), " +
                    "'yyyy-mm-ddThh24-mi-ss.maZ')) " +
                    "AND (:destFrom IS NULL OR destination_from = CAST(:destFrom AS TEXT)) " +
                    "AND (:destTo IS NULL OR destination_to = CAST(:destTo AS TEXT)) " +
                    "AND (:priceFrom = -1 OR price >= CAST(:priceFrom AS NUMERIC)) " +
                    "AND (:priceTo = -1 OR price <= CAST(:priceTo AS NUMERIC)) " +
                    "AND deleted_at IS NULL ",
            countQuery = "SELECT count(*) " +
                    "FROM flight " +
                    "WHERE (:dateFromString IS NULL OR date_time >= to_timestamp(CAST(:dateFromString AS TEXT), " +
                    "'yyyy-mm-ddThh24-mi-ss.maZ')) " +
                    "AND (:dateToString IS NULL OR date_time <= to_timestamp(CAST(:dateToString AS TEXT), " +
                    "'yyyy-mm-ddThh24-mi-ss.maZ')) " +
                    "AND (:destFrom IS NULL OR destination_from = CAST(:destFrom AS TEXT)) " +
                    "AND (:destTo IS NULL OR destination_to = CAST(:destTo AS TEXT)) " +
                    "AND (:priceFrom = -1 OR price >= CAST(:priceFrom AS NUMERIC)) " +
                    "AND (:priceTo = -1 OR price <= CAST(:priceTo AS NUMERIC)) " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<FlightEntity> findAllByParameters(@Param("dateFromString") String dateFromString,
                                           @Param("dateToString") String dateToString,
                                           @Param("destFrom") String destFrom,
                                           @Param("destTo") String destTo,
                                           @Param("priceFrom") double priceFrom,
                                           @Param("priceTo") double priceTo,
                                           Pageable pageable);

    @Query(value = "SELECT * " +
                    "FROM flight " +
                    "WHERE (:destFrom IS NULL OR destination_from = CAST(:destFrom AS TEXT)) " +
                    "AND (:destTo IS NULL OR destination_to = CAST(:destTo AS TEXT)) " +
                    "AND deleted_at IS NULL ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM flight " +
                    "WHERE (:destFrom IS NULL OR destination_from = CAST(:destFrom AS TEXT)) " +
                    "AND (:destTo IS NULL OR destination_to = CAST(:destTo AS TEXT)) " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<FlightEntity> findAllBySubscribe(@Param("destFrom") String destFrom,
                                          @Param("destTo") String destTo,
                                          Pageable pageable);

    @Query(value = "SELECT * " +
                    "FROM flight " +
                    "WHERE id = :flightId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    FlightEntity findOneById(@Param(value = "flightId") long flightId);

    @Query(value = "SELECT * " +
                        "FROM flight " +
                        "WHERE flight.id IN (:flightIds) " +
                        "AND deleted_at IS NULL ",
            countQuery = "SELECT COUNT(*) " +
                        "FROM flight " +
                        "WHERE flight.id IN (:flightIds) " +
                        "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<FlightEntity> findAllByIds(@Param("flightIds") List<Long> flightIds,
                                    Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE flight " +
                    "SET deleted_at = now() " +
                    "WHERE id = :flightId ",
            nativeQuery = true)
    void deleteOneById(@Param("flightId") long flightId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE flight " +
                    "SET deleted_at = now() " +
                    "WHERE now() > date_time ",
            nativeQuery = true)
    void clearNotActualRecords();
}