package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.TokenConverter;
import id.yellow.aircompany.converter.UserConverter;
import id.yellow.aircompany.entity.TokenEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.exception.BadRequestException;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.*;
import id.yellow.aircompany.repository.TokenRepository;
import id.yellow.aircompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserModel> getUsers(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);

        return UserConverter.toUserModels(userRepository.findAll(pageable).getContent());
    }

    @Override
    public UserModel getUserById(long id) {

        UserEntity userEntity = userRepository.findOneById(id);

        if(userEntity != null) {
            return UserConverter.toUserModel(userEntity);
        }

        throw new NotFoundException("User with this id is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin() or @securityUtility.isUserRole(#registerModel)")
    public void signUpUser(RegisterModel registerModel) {

        UserEntity userEntity = userRepository.findOneByUsername(registerModel.getUsername());

        if(userEntity == null) {
            userEntity = UserConverter.toUserEntity(registerModel, passwordEncoder.encode(registerModel.getPassword()));
            userRepository.save(userEntity);
        } else {
            throw new BadRequestException("User with the same username already exists!");
        }
    }

    @Override
    public TokenModel signInUser(LoginModel loginModel) {

        UserEntity userEntity = userRepository.findOneByUsername(loginModel.getUsername());

        if(userEntity != null && passwordEncoder.matches(loginModel.getPassword(), userEntity.getHashPassword())) {
            TokenEntity tokenEntity = TokenConverter.toTokenEntity(loginModel, userEntity.getId());

            tokenEntity = tokenRepository.save(tokenEntity);

            return TokenConverter.toTokenModel(tokenEntity);
        }

        throw new NotFoundException("User not found!");
    }
}
