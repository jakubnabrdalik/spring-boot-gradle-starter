package eu.solidcraft.sbgs.infrastructure.security;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class LoggedUserRepository {
    public String getLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!isAuthenticated(authentication)) {
            throw new SessionAuthenticationException("No user found in session");
        }
        return authentication.getName();
    }

    @VisibleForTesting public static boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
