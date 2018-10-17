package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    TokenEntity findOneByValue(String token);
}
