package id.yellow.aircompany.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@Data
@MappedSuperclass
public abstract class AuditableEntity {

    @Column(name = "created_at")
    protected Instant createdAt;

    @Column(name = "updated_at")
    protected Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
