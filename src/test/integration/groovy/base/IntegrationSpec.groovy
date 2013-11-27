package base
import eu.solidcraft.sbgs.conf.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes=[Application.class])
@Transactional
@TransactionConfiguration(defaultRollback = true)
abstract class IntegrationSpec extends Specification {
    @Autowired private AuthenticationManager authenticationManager
    @Autowired private Environment environment
    private BackendAuthenticator authenticator
    protected User user

    def setup() {
        authenticator = new BackendAuthenticator(authenticationManager)
        user = new User(environment.getProperty("security.user.name"), environment.getProperty("security.user.password"), [])
        login(user)
    }

    void login(User user) {
        authenticator.login(user.username, user.password)
    }
}
