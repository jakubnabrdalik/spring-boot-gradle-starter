package eu.solidcraft.sbgs.domain

import org.springframework.data.repository.CrudRepository

interface SomeEntityRepository extends CrudRepository<SomeEntity, Long> {
    List<SomeEntity> findByUsername(String username)
}