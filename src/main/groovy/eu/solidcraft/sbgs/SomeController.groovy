package eu.solidcraft.sbgs

import eu.solidcraft.sbgs.domain.SomeEntity
import eu.solidcraft.sbgs.domain.SomeEntityRepository
import eu.solidcraft.sbgs.infrastructure.security.LoggedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping

import static org.springframework.util.Assert.notNull

@Controller
@Transactional
class SomeController {
    private SomeEntityRepository someEntityRepository
    private LoggedUserRepository loggedUserRepository

    protected SomeController() { //required by cglib
    }

    @Autowired
    SomeController(SomeEntityRepository someEntityRepository, LoggedUserRepository loggedUserRepository) {
        this.someEntityRepository = someEntityRepository
        this.loggedUserRepository = loggedUserRepository
    }

    @RequestMapping(value = "/some/mine", produces="application/json")
    Map mine() {
        String username = loggedUserRepository.getLoggedUserName();
        List<SomeEntity> entities = someEntityRepository.findByUsername(username);
        return ["entities": entities]
    }

    @RequestMapping(value = "/some/add", produces="application/json")
    Map<String, Object> add(BigDecimal amount) {
        notNull(amount)
        String username = loggedUserRepository.getLoggedUserName();
        SomeEntity entity = new SomeEntity(username, amount, new Date())
        someEntityRepository.save(entity)
        return ["wasCreated": true, "entity": entity]
    }
}
