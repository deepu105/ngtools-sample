package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityWithDTODTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityWithDTO and its DTO EntityWithDTODTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityWithDTOMapper extends EntityMapper <EntityWithDTODTO, EntityWithDTO> {
    
    
    default EntityWithDTO fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityWithDTO entityWithDTO = new EntityWithDTO();
        entityWithDTO.setId(id);
        return entityWithDTO;
    }
}
