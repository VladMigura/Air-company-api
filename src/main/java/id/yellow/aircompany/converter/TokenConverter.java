package id.yellow.aircompany.converter;

import id.yellow.aircompany.entity.TokenEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.model.LoginModel;
import id.yellow.aircompany.model.TokenModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class TokenConverter {

    public static TokenModel toTokenModel(TokenEntity tokenEntity) {

        return TokenModel.builder()
                .id(tokenEntity.getId())
                .value(tokenEntity.getValue())
                .userId(tokenEntity.getUserEntity().getId())
                .build();
    }

    public static List<TokenModel> toTokenModels(List<TokenEntity> tokenEntities) {

        List<TokenModel> tokenModels = new ArrayList<>();

        tokenEntities.forEach(tokenEntity -> {
            tokenModels.add(TokenConverter.toTokenModel(tokenEntity));
        });

        return tokenModels;
    }

    public static TokenEntity toTokenEntity(LoginModel loginModel, long userId) {

        return TokenEntity.builder()
                .value(RandomStringUtils.random(10, true, true))
                .userEntity(UserEntity.builder().id(userId).build())
                .build();
    }
}
