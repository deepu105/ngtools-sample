package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FieldTestServiceImplEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FieldTestServiceImplEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldTestServiceImplEntityRepository extends JpaRepository<FieldTestServiceImplEntity,Long> {
    
}
