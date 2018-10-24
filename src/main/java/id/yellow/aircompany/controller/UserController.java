package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.*;
import id.yellow.aircompany.service.UserDiscountService;
import id.yellow.aircompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDiscountService userDiscountService;

    @GetMapping("/users")
    public List<UserModel> getUsers(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {

        return userService.getUsers(page, pageSize);
    }

    @GetMapping("/users/{userId}")
    public UserModel getUserById(@PathVariable long userId) {

        return userService.getUserById(userId);
    }

    @GetMapping("/users/{userId}/discounts")
    public List<UserDiscountModel> getUserDiscounts(@PathVariable long userId,
                                                    @RequestParam(name = "page", required = false,
                                                            defaultValue = "1") int page,
                                                    @RequestParam(name = "pageSize", required = false,
                                                            defaultValue = "20") int pageSize) {

        return userDiscountService.getUserDiscounts(userId, page, pageSize);
    }

    @PostMapping("/users/{userId}/discounts")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDiscountModel createUserDiscount(@PathVariable long userId,
                                                @RequestBody UserDiscountModel userDiscountModel) {

        return userDiscountService.createUserDiscount(userId, userDiscountModel);
    }

    @DeleteMapping("/users/discounts/{userDiscountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserDiscount(@PathVariable long userDiscountId) {

        userDiscountService.deleteUserDiscount(userDiscountId);
    }

    @PostMapping("/users/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpUser(@RequestBody RegisterModel registerModel) {

        userService.signUpUser(registerModel);
    }

    @PostMapping("/users/signIn")
    public TokenModel signInUser(@RequestBody LoginModel loginModel) {

        return userService.signInUser(loginModel);
    }
}
