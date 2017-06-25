package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceClassAndDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceClassAndDTO entity.
 */
public interface EntityWithServiceClassAndDTOSearchRepository extends ElasticsearchRepository<EntityWithServiceClassAndDTO, Long> {
}
