package dev.udhayakumar.codegists.auth;

import dev.udhayakumar.codegists.users.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    public static String getAuthenticatedUsername() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                return authentication.getName();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
