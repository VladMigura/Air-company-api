package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.UserDiscountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserDiscountRepository extends JpaRepository<UserDiscountEntity, Long> {

    @Query(value = "SELECT * FROM user_discount ",
            countQuery = "SELECT COUNT(*) FROM user_discount ",
            nativeQuery = true)
    Page<UserDiscountEntity> findAll(Pageable pageable);

    UserDiscountEntity findOneById(long id);

    @Transactional
    void deleteOneById(long id);
}
