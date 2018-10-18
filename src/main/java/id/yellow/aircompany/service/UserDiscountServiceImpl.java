package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.UserDiscountConverter;
import id.yellow.aircompany.entity.UserDiscountEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.UserDiscountModel;
import id.yellow.aircompany.repository.UserDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDiscountServiceImpl implements UserDiscountService {

    @Autowired
    private UserDiscountRepository userDiscountRepository;

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public UserDiscountModel createUserDiscount(UserDiscountModel userDiscountModel) {

        userDiscountRepository.save(UserDiscountConverter.toUserDiscountEntity(userDiscountModel));
        return userDiscountModel;
    }

    @Override
    @PreAuthorize("@securityUtility.isOwnerById(#id) or @securityUtility.isAdmin()")
    public List<UserDiscountModel> getUserDiscounts(long id, int page, int pageSize) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);

        return UserDiscountConverter.toUserDiscountModels(userDiscountRepository
                .findAll(pageable).getContent());
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public void deleteUserDiscount(long id) {

        UserDiscountEntity userDiscountEntity = userDiscountRepository.findOneById(id);

        if(userDiscountEntity != null) {
            userDiscountRepository.deleteOneById(id);
        } else {
            throw new NotFoundException("User discount is not found!");
        }
    }
}
