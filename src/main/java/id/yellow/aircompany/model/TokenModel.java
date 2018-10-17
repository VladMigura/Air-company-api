package id.yellow.aircompany.model;

import id.yellow.aircompany.entity.TokenEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenModel {

    private String value;

    public static TokenModel from(TokenEntity tokenEntity) {

        return TokenModel.builder()
                .value(tokenEntity.getValue())
                .build();
    }
}
