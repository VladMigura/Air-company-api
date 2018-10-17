package id.yellow.aircompany.security.detail;

import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.exception.UnauthorizedException;
import id.yellow.aircompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findOneByUsername(username);

        if(userEntity == null) throw new UnauthorizedException("User not found!");

        return new UserDetailsImpl(userEntity);
    }
}
