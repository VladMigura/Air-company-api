package id.yellow.aircompany.service;

import id.yellow.aircompany.model.UserDiscountModel;

import java.util.List;

public interface UserDiscountService {

    UserDiscountModel createUserDiscount(UserDiscountModel userDiscountModel);
    List<UserDiscountModel> getUserDiscounts(long id, int page, int pageSize);
    void deleteUserDiscount(long id);
}
