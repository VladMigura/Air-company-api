package id.yellow.aircompany.model;

import id.yellow.aircompany.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    private String username;
    private String role;

    public static UserModel from(UserEntity userEntity) {

        return UserModel.builder()
                .username(userEntity.getUsername())
                .role(userEntity.getRole().toString())
                .build();
    }
}
