package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.LoginModel;
import id.yellow.aircompany.model.RegisterModel;
import id.yellow.aircompany.model.TokenModel;
import id.yellow.aircompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
