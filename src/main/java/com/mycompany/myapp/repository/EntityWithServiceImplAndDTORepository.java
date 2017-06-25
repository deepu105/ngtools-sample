package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EntityWithServiceImplAndDTO;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EntityWithServiceImplAndDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityWithServiceImplAndDTORepository extends JpaRepository<EntityWithServiceImplAndDTO,Long> {
    
}
