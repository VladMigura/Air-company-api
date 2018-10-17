package id.yellow.aircompany.service;

import id.yellow.aircompany.model.LoginModel;
import id.yellow.aircompany.model.RegisterModel;
import id.yellow.aircompany.model.TokenModel;

public interface UserService {

    void signUpUser(RegisterModel registerModel);
    TokenModel signInUser(LoginModel loginModel);
}
