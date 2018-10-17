package id.yellow.aircompany.entity;

import id.yellow.aircompany.enumeration.Role;
import id.yellow.aircompany.model.RegisterModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    private List<SubscribeEntity> subscribeEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<TicketEntity> ticketEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<TokenEntity> tokenEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<UserDiscountEntity> userDiscountEntities;
}
