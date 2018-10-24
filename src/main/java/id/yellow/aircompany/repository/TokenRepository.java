package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    TokenEntity findOneByValue(String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE token " +
                    "SET deleted_at = now() " +
                    "WHERE user_id = :userId ",
            nativeQuery = true)
    void deleteOneByUserId(@Param("userId") long userId);
}
