package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.UserDiscountConverter;
import id.yellow.aircompany.entity.UserDiscountEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.UserDiscountModel;
import id.yellow.aircompany.repository.UserDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDiscountServiceImpl implements UserDiscountService {

    @Autowired
    private UserDiscountRepository userDiscountRepository;

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public UserDiscountModel createUserDiscount(long userId, UserDiscountModel userDiscountModel) {

        userDiscountModel.setUserId(userId);
        return UserDiscountConverter.toUserDiscountModel(userDiscountRepository
                .save(UserDiscountConverter.toUserDiscountEntity(userDiscountModel)));
    }

    @Override
    @PreAuthorize("@securityUtility.isOwnerById(#userId) or @securityUtility.isAdmin()")
    public List<UserDiscountModel> getUserDiscounts(long userId, int page, int pageSize) {

        return UserDiscountConverter.toUserDiscountModels(userDiscountRepository
                .findAllByUserId(userId, PageRequest.of(page - 1, pageSize)).getContent());
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public void deleteUserDiscount(long userDiscountId) {

        UserDiscountEntity userDiscountEntity = userDiscountRepository.findOneById(userDiscountId);

        if(userDiscountEntity != null) {
            userDiscountRepository.deleteOneById(userDiscountId);
        } else {
            throw new NotFoundException("User discount is not found!");
        }
    }
}
