package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.WaitingRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface WaitingListRepository extends JpaRepository<WaitingRecordEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM waiting_table " +
                    "WHERE id = :waitingRecordId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    WaitingRecordEntity findOneById(@Param("waitingRecordId") long waitingRecordId);

    @Query(value = "SELECT * " +
                    "FROM waiting_table " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM waiting_table " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL",
            nativeQuery = true)
    Page<WaitingRecordEntity> findAllByUserId(@Param("userId") long userId,
                                             Pageable pageable);

    @Query(value = "SELECT * " +
                    "FROM waiting_table " +
                    "WHERE deleted_at IS NULL ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM waiting_table " +
                    "WHERE deleted_at IS NULL ",
            nativeQuery = true)
    Page<WaitingRecordEntity> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE waiting_table " +
                    "SET deleted_at = now() " +
                    "WHERE id = :waitingRecordId ",
            nativeQuery = true)
    void deleteOneById(@Param("waitingRecordId") long waitingRecordId);


}
