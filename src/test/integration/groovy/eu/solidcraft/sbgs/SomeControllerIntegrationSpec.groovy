
package eu.solidcraft.sbgs
import base.IntegrationSpec
import eu.solidcraft.sbgs.domain.SomeEntity
import eu.solidcraft.sbgs.domain.SomeEntityRepository
import org.springframework.beans.factory.annotation.Autowired

class SomeControllerIntegrationSpec extends IntegrationSpec {
    @Autowired SomeController someController
    @Autowired SomeEntityRepository someEntityRepository
    BigDecimal amount = new BigDecimal(1000)

    def "add should return new entity"() {
        when:
            Map result = someController.add(amount)

        then:
            result.wasCreated == true
            result.entity.someAmount == amount
            result.entity.id != null
    }

    def "add should save entity to DB"() {
        when:
            someController.add(amount)

        then:
            List<SomeEntity> entities = someEntityRepository.findByUsername(user.username)
            entities.size() == 1
            entities.first().someAmount == amount
    }


    def "should show my entities"() {
        given:
            Map result = someController.add(amount)
            SomeEntity createdEntity = result.entity

        when:
            Map response = someController.mine()

        then:
            response.entities.equals([createdEntity])
    }
}
