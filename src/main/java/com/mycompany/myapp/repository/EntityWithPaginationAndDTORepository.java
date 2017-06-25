package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EntityWithPaginationAndDTO;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EntityWithPaginationAndDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityWithPaginationAndDTORepository extends JpaRepository<EntityWithPaginationAndDTO,Long> {
    
}
