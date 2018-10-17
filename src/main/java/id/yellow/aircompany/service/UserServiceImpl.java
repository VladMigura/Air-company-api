package id.yellow.aircompany.service;

import id.yellow.aircompany.model.LoginModel;
import id.yellow.aircompany.model.RegisterModel;
import id.yellow.aircompany.model.TokenModel;
import id.yellow.aircompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registerUser(RegisterModel registerModel) {
        return false;
    }

    @Override
    public TokenModel loginUser(LoginModel loginModel) {
        return null;
    }
}
