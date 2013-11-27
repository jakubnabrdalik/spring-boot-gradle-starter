package eu.solidcraft.sbgs
import base.BackendAuthenticator
import base.MvcIntegrationTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class JsonVieResolverMvcTest extends MvcIntegrationTest {
    String JSON_MEDIA_TYPE = "application/json"
    @Autowired AuthenticationManager authenticationManager
    @Autowired private Environment environment

    @Test
    void "view resolver should render JSON"() {
        //given
        userIsLoggedIn()

        //when then
        mockMvc.perform(
                    post('/some/mine')
                ).
                andExpect(status().isOk()).
                andExpect(content().contentType(JSON_MEDIA_TYPE))
    }

    private void userIsLoggedIn() {
        new BackendAuthenticator(authenticationManager).
                login(environment.getProperty("security.user.name"), environment.getProperty("security.user.password"))
    }
}
