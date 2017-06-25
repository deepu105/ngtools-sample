package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceImplAndDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceImplAndDTO entity.
 */
public interface EntityWithServiceImplAndDTOSearchRepository extends ElasticsearchRepository<EntityWithServiceImplAndDTO, Long> {
}
