package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.FlightDiscountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FlightDiscountRepository extends JpaRepository<FlightDiscountEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM flight_discount " +
                    "WHERE (:valueTo IS NULL OR flight_discount.value >= :valueTo) ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM flight_discount " +
                    "WHERE (:valueTo IS NULL OR flight_discount.value >= :valueTo) ",
            nativeQuery = true)
    Page<FlightDiscountEntity> findAllByParameters(@Param("valueTo") int valueTo,
                                                   Pageable pageable);

    FlightDiscountEntity findOneById(long id);

    @Transactional
    void deleteOneById(long id);
}
