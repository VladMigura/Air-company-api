package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query(value = "", nativeQuery = true)
    Page<FlightEntity> findAllByParameters(@Param("dateFrom") Instant dateFrom, @Param("dateTo") Instant dateTo,
                                           @Param("destFrom") String destFrom, @Param("destTo") String destTo,
                                           @Param("priceFrom") BigDecimal priceFrom, @Param("priceTo") BigDecimal priceTo,
                                           Pageable pageable);
}
