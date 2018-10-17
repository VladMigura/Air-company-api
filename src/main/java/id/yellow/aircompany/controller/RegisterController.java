package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.RegisterModel;
import id.yellow.aircompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(RegisterModel registerModel) {

        userService.registerUser(registerModel);
    }
}
