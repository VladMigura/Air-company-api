package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUsername(String username);
}
