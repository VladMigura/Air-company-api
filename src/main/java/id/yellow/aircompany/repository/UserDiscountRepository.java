package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.UserDiscountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDiscountRepository extends JpaRepository<UserDiscountEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM user_discount " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM user_discount " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<UserDiscountEntity> findAllByUserId(@Param(value = "userId") long userId, Pageable pageable);

    @Query(value = "SELECT * " +
                    "FROM user_discount " +
                    "WHERE id = :userDiscountId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    UserDiscountEntity findOneById(@Param(value = "userDiscountId") long userDiscountId);

    @Query(value = "SELECT * " +
            "FROM user_discount " +
            "WHERE user_id = :userId " +
            "AND to_date > now() " +
            "AND deleted_at IS NULL " +
            "ORDER BY from_date ASC ",
            nativeQuery = true)
    List<UserDiscountEntity> findActualAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_discount " +
                    "SET deleted_at = now() " +
                    "WHERE id = :userDiscountId ",
            nativeQuery = true)
    void deleteOneById(@Param(value = "userDiscountId") long userDiscountId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_discount " +
                    "SET deleted_at = now() " +
                    "WHERE now() > to_date ",
            nativeQuery = true)
    void clearNotActualRecords();
}
