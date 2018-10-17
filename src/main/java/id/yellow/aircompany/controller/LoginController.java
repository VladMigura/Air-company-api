package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.LoginModel;
import id.yellow.aircompany.model.TokenModel;
import id.yellow.aircompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public TokenModel loginUser(@RequestBody LoginModel loginModel) {

        return userService.loginUser(loginModel);
    }
}
