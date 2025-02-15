package dev.udhayakumar.codegists.auth;

import dev.udhayakumar.codegists.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    public static String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUserName();
        }
        throw new RuntimeException("Invalid authentication");
    }
}
