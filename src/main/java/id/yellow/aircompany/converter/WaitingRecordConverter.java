package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.WaitingRecordEntity;
import id.yellow.aircompany.model.WaitingRecordModel;

import java.util.ArrayList;
import java.util.List;

public class WaitingRecordConverter {

    public static WaitingRecordModel toWaitingRecordModel(WaitingRecordEntity waitingRecordEntity) {

        return WaitingRecordModel.builder()
                .id(waitingRecordEntity.getId())
                .userId(waitingRecordEntity.getUserEntity().getId())
                .destinationTo(waitingRecordEntity.getDestinationTo())
                .build();
    }

    public static List<WaitingRecordModel> toWaitingRecordModels(List<WaitingRecordEntity> waitingRecordEntities) {

        List<WaitingRecordModel> waitingRecordModels = new ArrayList<>();

        waitingRecordEntities.forEach(waitingRecordEntity -> {
            waitingRecordModels.add(WaitingRecordConverter.toWaitingRecordModel(waitingRecordEntity));
        });

        return waitingRecordModels;
    }
}
