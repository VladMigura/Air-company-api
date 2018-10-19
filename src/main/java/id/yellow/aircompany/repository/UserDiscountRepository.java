package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.UserDiscountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDiscountRepository extends JpaRepository<UserDiscountEntity, Long> {

    @Query(value = "SELECT * FROM user_discount ",
            countQuery = "SELECT COUNT(*) FROM user_discount ",
            nativeQuery = true)
    Page<UserDiscountEntity> findAll(Pageable pageable);

    UserDiscountEntity findOneById(long id);

    @Query(value = "SELECT * " +
            "FROM user_discount " +
            "WHERE user_id = :userId " +
            "AND to_date > now() " +
            "ORDER BY from_date ASC ",
            nativeQuery = true)
    List<UserDiscountEntity> findActualAllByUserId(@Param("userId") Long userId);

    @Transactional
    void deleteOneById(long id);
}
