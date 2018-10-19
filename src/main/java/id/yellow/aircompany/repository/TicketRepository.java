package id.yellow.aircompany.repository;

import id.yellow.aircompany.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    TicketEntity findOneById(long id);
    List<TicketEntity> findAllByUserEntityId(long id);
}
