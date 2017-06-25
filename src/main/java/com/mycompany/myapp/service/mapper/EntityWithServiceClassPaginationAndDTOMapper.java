package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityWithServiceClassPaginationAndDTODTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityWithServiceClassPaginationAndDTO and its DTO EntityWithServiceClassPaginationAndDTODTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityWithServiceClassPaginationAndDTOMapper extends EntityMapper <EntityWithServiceClassPaginationAndDTODTO, EntityWithServiceClassPaginationAndDTO> {
    
    
    default EntityWithServiceClassPaginationAndDTO fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = new EntityWithServiceClassPaginationAndDTO();
        entityWithServiceClassPaginationAndDTO.setId(id);
        return entityWithServiceClassPaginationAndDTO;
    }
}
