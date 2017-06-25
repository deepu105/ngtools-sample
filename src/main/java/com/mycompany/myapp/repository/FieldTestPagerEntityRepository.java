package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FieldTestPagerEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FieldTestPagerEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldTestPagerEntityRepository extends JpaRepository<FieldTestPagerEntity,Long> {
    
}
