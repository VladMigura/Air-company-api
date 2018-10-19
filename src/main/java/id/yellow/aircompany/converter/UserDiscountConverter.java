package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.UserDiscountEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.model.UserDiscountModel;

import java.util.ArrayList;
import java.util.List;

public class UserDiscountConverter {

    public static UserDiscountModel toUserDiscountModel(UserDiscountEntity userDiscountEntity) {

        return UserDiscountModel.builder()
                .id(userDiscountEntity.getId())
                .value(userDiscountEntity.getValue())
                .userId(userDiscountEntity.getUserEntity().getId())
                .fromDate(userDiscountEntity.getFromDate())
                .toDate(userDiscountEntity.getToDate())
                .build();
    }

    public static List<UserDiscountModel> toUserDiscountModels(List<UserDiscountEntity> userDiscountEntities) {

        List<UserDiscountModel> userDiscountModels = new ArrayList<>();

        userDiscountEntities.forEach(userDiscountEntity -> {
            userDiscountModels.add(UserDiscountConverter.toUserDiscountModel(userDiscountEntity));
        });

        return userDiscountModels;
    }

    public static UserDiscountEntity toUserDiscountEntity(UserDiscountModel userDiscountModel) {

        return UserDiscountEntity.builder()
                .value(userDiscountModel.getValue())
                .userEntity(UserEntity.builder().id(userDiscountModel.getUserId()).build())
                .fromDate(userDiscountModel.getFromDate())
                .toDate(userDiscountModel.getToDate())
                .build();
    }

    public static List<UserDiscountEntity> toUserDiscountEntities(List<UserDiscountModel> userDiscountModels) {

        List<UserDiscountEntity> userDiscountEntities = new ArrayList<>();

        userDiscountModels.forEach(userDiscountModel -> {
            userDiscountEntities.add(UserDiscountConverter.toUserDiscountEntity(userDiscountModel));
        });

        return userDiscountEntities;
    }
}
