package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
                    "AND (:priceTo = -1 OR price <= CAST(:priceTo AS NUMERIC)) ",
            countQuery = "SELECT count(*) " +
                    "FROM flight " +
                    "WHERE (:dateFromString IS NULL OR date_time >= to_timestamp(CAST(:dateFromString AS TEXT), " +
                    "'yyyy-mm-ddThh24-mi-ss.maZ')) " +
                    "AND (:dateToString IS NULL OR date_time <= to_timestamp(CAST(:dateToString AS TEXT), " +
                    "'yyyy-mm-ddThh24-mi-ss.maZ')) " +
                    "AND (:destFrom IS NULL OR destination_from = CAST(:destFrom AS TEXT)) " +
                    "AND (:destTo IS NULL OR destination_to = CAST(:destTo AS TEXT)) " +
                    "AND (:priceFrom = -1 OR price >= CAST(:priceFrom AS NUMERIC)) " +
                    "AND (:priceTo = -1 OR price <= CAST(:priceTo AS NUMERIC)) ",
            nativeQuery = true)
    Page<FlightEntity> findAllByParameters(@Param("dateFromString") String dateFromString,
                                           @Param("dateToString") String dateToString,
                                           @Param("destFrom") String destFrom,
                                           @Param("destTo") String destTo,
                                           @Param("priceFrom") double priceFrom,
                                           @Param("priceTo") double priceTo,
                                           Pageable pageable);

    FlightEntity findOneById(long id);

    @Query(value = "SELECT * " +
                        "FROM flight " +
                        "WHERE flight.id IN (:ids) ",
            countQuery = "SELECT COUNT(*) " +
                        "FROM flight " +
                        "WHERE flight.id IN (:ids) ",
            nativeQuery = true)
    Page<FlightEntity> findAllByIds(@Param("ids") List<Long> ids,
                                    Pageable pageable);

    @Transactional
    void deleteOneById(long id);
}
