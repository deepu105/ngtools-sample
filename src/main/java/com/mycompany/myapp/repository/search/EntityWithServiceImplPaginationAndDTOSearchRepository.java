package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceImplPaginationAndDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceImplPaginationAndDTO entity.
 */
public interface EntityWithServiceImplPaginationAndDTOSearchRepository extends ElasticsearchRepository<EntityWithServiceImplPaginationAndDTO, Long> {
}
