package id.yellow.aircompany.service;

import id.yellow.aircompany.model.*;

import java.util.List;

public interface UserService {

    List<UserModel> getUsers(int page, int pageSize);
    UserModel getUserById(long id);

    void signUpUser(RegisterModel registerModel);
    TokenModel signInUser(LoginModel loginModel);
}
