package base

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

import static eu.solidcraft.sbgs.infrastructure.security.LoggedUserRepository.isAuthenticated

class BackendAuthenticator {
    private AuthenticationManager authenticationManager

    BackendAuthenticator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager
    }

    boolean login(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password))
        boolean isAuthenticated = isAuthenticated(authentication)
        if (isAuthenticated) {
            SecurityContextHolder.getContext().setAuthentication(authentication)
        }
        return isAuthenticated
    }

    boolean logout() {
        SecurityContextHolder.getContext().setAuthentication(null)
    }

}
