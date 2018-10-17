package id.yellow.aircompany.model;

import id.yellow.aircompany.entity.SubscribeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeModel {

    private String destinationFrom;
    private String destinationTo;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private Instant dateFrom;
    private Instant dateTo;

    public static SubscribeModel from(SubscribeEntity subscribeEntity) {

        return SubscribeModel.builder()
                .destinationFrom(subscribeEntity.getDestinationFrom())
                .destinationTo(subscribeEntity.getDestinationTo())
                .priceFrom(subscribeEntity.getPriceFrom())
                .priceTo(subscribeEntity.getPriceTo())
                .dateFrom(subscribeEntity.getDateFrom())
                .dateTo(subscribeEntity.getDateTo())
                .build();
    }

    public static List<SubscribeModel> from(List<SubscribeEntity> subscribeEntities) {

        List<SubscribeModel> subscribeModels = new ArrayList<>();

        subscribeEntities.forEach(subscribeEntity -> {
            subscribeModels.add(SubscribeModel.from(subscribeEntity));
        });

        return subscribeModels;
    }
}
