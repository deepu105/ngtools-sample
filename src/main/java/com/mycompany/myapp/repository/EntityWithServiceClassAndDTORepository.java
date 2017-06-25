package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EntityWithServiceClassAndDTO;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EntityWithServiceClassAndDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityWithServiceClassAndDTORepository extends JpaRepository<EntityWithServiceClassAndDTO,Long> {
    
}
