package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FieldTestMapstructEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FieldTestMapstructEntity and its DTO FieldTestMapstructEntityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FieldTestMapstructEntityMapper extends EntityMapper <FieldTestMapstructEntityDTO, FieldTestMapstructEntity> {
    
    
    default FieldTestMapstructEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        FieldTestMapstructEntity fieldTestMapstructEntity = new FieldTestMapstructEntity();
        fieldTestMapstructEntity.setId(id);
        return fieldTestMapstructEntity;
    }
}
