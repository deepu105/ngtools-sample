package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityWithServiceImplPaginationAndDTODTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EntityWithServiceImplPaginationAndDTO and its DTO EntityWithServiceImplPaginationAndDTODTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityWithServiceImplPaginationAndDTOMapper extends EntityMapper <EntityWithServiceImplPaginationAndDTODTO, EntityWithServiceImplPaginationAndDTO> {
    
    
    default EntityWithServiceImplPaginationAndDTO fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO();
        entityWithServiceImplPaginationAndDTO.setId(id);
        return entityWithServiceImplPaginationAndDTO;
    }
}
