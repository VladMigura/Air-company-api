package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM user_table " +
                    "WHERE deleted_at IS NULL ",
            countQuery = "SELECT count(*) " +
                    "FROM user_table " +
                    "WHERE deleted_at IS NULL ",
            nativeQuery = true)
    Page<UserEntity> findAll(Pageable pageable);

    @Query(value = "SELECT * " +
                    "FROM user_table " +
                    "WHERE id = :userId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    UserEntity findOneById(@Param("userId") long userId);

    @Query(value = "SELECT * " +
                    "FROM user_table " +
                    "WHERE username = :username " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    UserEntity findOneByUsername(@Param(value = "username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_table " +
                    "SET deleted_at = now() " +
                    "WHERE id = :userId ",
            nativeQuery = true)
    void deleteOneById(@Param(value = "userId") long userId);
}
