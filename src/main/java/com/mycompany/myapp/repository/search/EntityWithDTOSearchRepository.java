package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithDTO entity.
 */
public interface EntityWithDTOSearchRepository extends ElasticsearchRepository<EntityWithDTO, Long> {
}
