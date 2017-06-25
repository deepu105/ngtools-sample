package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FieldTestPaginationEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FieldTestPaginationEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldTestPaginationEntityRepository extends JpaRepository<FieldTestPaginationEntity,Long> {
    
}
