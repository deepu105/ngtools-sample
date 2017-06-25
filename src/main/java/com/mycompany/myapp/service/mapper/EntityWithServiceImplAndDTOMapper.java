package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityWithServiceImplAndDTODTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityWithServiceImplAndDTO and its DTO EntityWithServiceImplAndDTODTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityWithServiceImplAndDTOMapper extends EntityMapper <EntityWithServiceImplAndDTODTO, EntityWithServiceImplAndDTO> {
    
    
    default EntityWithServiceImplAndDTO fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = new EntityWithServiceImplAndDTO();
        entityWithServiceImplAndDTO.setId(id);
        return entityWithServiceImplAndDTO;
    }
}
