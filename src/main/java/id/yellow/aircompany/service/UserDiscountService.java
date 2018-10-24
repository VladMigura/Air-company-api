package id.yellow.aircompany.service;

import id.yellow.aircompany.model.UserDiscountModel;

import java.util.List;

public interface UserDiscountService {

    UserDiscountModel createUserDiscount(long userId, UserDiscountModel userDiscountModel);
    List<UserDiscountModel> getUserDiscounts(long userId, int page, int pageSize);
    void deleteUserDiscount(long userDiscountId);
}
