package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EntityWithServiceImpl;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EntityWithServiceImpl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityWithServiceImplRepository extends JpaRepository<EntityWithServiceImpl,Long> {
    
}
