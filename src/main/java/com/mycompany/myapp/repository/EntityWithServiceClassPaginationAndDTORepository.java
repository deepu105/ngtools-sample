package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EntityWithServiceClassPaginationAndDTO;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EntityWithServiceClassPaginationAndDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityWithServiceClassPaginationAndDTORepository extends JpaRepository<EntityWithServiceClassPaginationAndDTO,Long> {
    
}
