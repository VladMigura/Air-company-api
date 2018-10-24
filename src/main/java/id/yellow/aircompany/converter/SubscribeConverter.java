package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.SubscribeEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.model.SubscribeModel;

import java.util.ArrayList;
import java.util.List;

public class SubscribeConverter {

    public static SubscribeModel toSubscribeModel(SubscribeEntity subscribeEntity) {

        return SubscribeModel.builder()
                .id(subscribeEntity.getId())
                .destinationFrom(subscribeEntity.getDestinationFrom())
                .destinationTo(subscribeEntity.getDestinationTo())
                .userId(subscribeEntity.getUserEntity().getId())
                .build();
    }

    public static List<SubscribeModel> toSubscribeModels(List<SubscribeEntity> subscribeEntities) {

        List<SubscribeModel> subscribeModels = new ArrayList<>();

        subscribeEntities.forEach(subscribeEntity -> {
            subscribeModels.add(SubscribeConverter.toSubscribeModel(subscribeEntity));
        });

        return subscribeModels;
    }

    public static SubscribeEntity toSubscribeEntity(SubscribeModel subscribeModel) {

        return SubscribeEntity.builder()
                .destinationFrom(subscribeModel.getDestinationFrom())
                .destinationTo(subscribeModel.getDestinationTo())
                .userEntity(UserEntity.builder().id(subscribeModel.getUserId()).build())
                .build();
    }

    public List<SubscribeEntity> toSubscribeEntities(List<SubscribeModel> subscribeModels) {

        List<SubscribeEntity> subscribeEntities = new ArrayList<>();

        subscribeModels.forEach(subscribeModel -> {
            subscribeEntities.add(SubscribeConverter.toSubscribeEntity(subscribeModel));
        });

        return subscribeEntities;
    }
}
