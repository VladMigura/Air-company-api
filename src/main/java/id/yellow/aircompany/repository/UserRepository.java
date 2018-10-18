package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user_table ",
            countQuery = "SELECT count(*) FROM user_table ",
            nativeQuery = true)
    Page<UserEntity> findAll(Pageable pageable);

    UserEntity findOneById(long id);

    UserEntity findOneByUsername(String username);
}
