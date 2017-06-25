package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithPaginationAndDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithPaginationAndDTO entity.
 */
public interface EntityWithPaginationAndDTOSearchRepository extends ElasticsearchRepository<EntityWithPaginationAndDTO, Long> {
}
