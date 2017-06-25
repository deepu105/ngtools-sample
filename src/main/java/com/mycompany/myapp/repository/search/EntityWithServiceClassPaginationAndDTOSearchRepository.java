package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceClassPaginationAndDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceClassPaginationAndDTO entity.
 */
public interface EntityWithServiceClassPaginationAndDTOSearchRepository extends ElasticsearchRepository<EntityWithServiceClassPaginationAndDTO, Long> {
}
