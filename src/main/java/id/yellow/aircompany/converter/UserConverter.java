package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.enumeration.Role;
import id.yellow.aircompany.model.RegisterModel;
import id.yellow.aircompany.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserModel toUserModel(UserEntity userEntity) {

        return UserModel.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .role(userEntity.getRole().toString())
                .build();
    }

    public static List<UserModel> toUserModels(List<UserEntity> userEntities) {

        List<UserModel> userModels = new ArrayList<>();

        userEntities.forEach(userEntity -> {
            userModels.add(UserConverter.toUserModel(userEntity));
        });

        return userModels;
    }

    public static UserEntity toUserEntity(RegisterModel registerModel, String hashPassword) {

        return UserEntity.builder()
                .username(registerModel.getUsername())
                .hashPassword(hashPassword)
                .role(Role.valueOf(registerModel.getRole()))
                .build();
    }
}
