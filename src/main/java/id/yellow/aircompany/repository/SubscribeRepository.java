package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.SubscribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SubscribeRepository extends JpaRepository<SubscribeEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM subscribe " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    List<SubscribeEntity> findAllByUserId(@Param("userId") long userId);

    @Query(value = "SELECT * " +
                    "FROM subscribe " +
                    "WHERE id = :subscribeId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    SubscribeEntity findOneById(@Param("subscribeId") long subscribeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE subscribe " +
                    "SET deleted_at = now() " +
                    "WHERE id = :subscribeId ",
            nativeQuery = true)
    void deleteOneById(@Param("subscribeId") long subscribeId);
}
