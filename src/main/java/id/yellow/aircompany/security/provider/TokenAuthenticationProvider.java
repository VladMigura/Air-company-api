package id.yellow.aircompany.security.provider;

import id.yellow.aircompany.entity.TokenEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.exception.UnauthorizedException;
import id.yellow.aircompany.repository.TokenRepository;
import id.yellow.aircompany.security.authentication.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    @Qualifier(value = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        String token = tokenAuthentication.getName();

        TokenEntity tokenEntity = tokenRepository.findOneByValue(token);

        if(tokenEntity == null) {
            throw new UnauthorizedException("Token not found!");
        }

        UserEntity userEntity = tokenEntity.getUserEntity();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

        tokenAuthentication.setUserDetails(userDetails);
        tokenAuthentication.setAuthenticated(true);

        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
