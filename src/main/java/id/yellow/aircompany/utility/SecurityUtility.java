package id.yellow.aircompany.utility;

import id.yellow.aircompany.model.RegisterModel;
import id.yellow.aircompany.security.detail.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value = "securityUtility")
public class SecurityUtility {

    public static boolean isAdmin() {

        if(!isAuthenticated()) {
            return false;
        }

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext()
                                                                .getAuthentication().getPrincipal();

        if(userDetailsImpl.getRole().equals("ADMIN")) {
            return true;
        }

        return false;
    }

    public static boolean isOwnerById(long userId) {

        if(!isAuthenticated()) {
            return false;
        }

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext()
                                                                .getAuthentication().getPrincipal();

        if(userDetailsImpl.getId() == userId) {
            return true;
        }

        return false;
    }

    public static boolean isOwnerByUsername(String username) {

        if(!isAuthenticated()) {
            return false;
        }

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext()
                                                                .getAuthentication().getPrincipal();

        if(userDetailsImpl.getUsername() == username) {
            return true;
        }

        return false;
    }

    public static boolean isAuthenticated() {

        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetailsImpl;
    }

    public static boolean isUserRole(RegisterModel registerModel) {

        return registerModel.getRole().equals("USER");
    }

    public static long getUserId() {

        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
