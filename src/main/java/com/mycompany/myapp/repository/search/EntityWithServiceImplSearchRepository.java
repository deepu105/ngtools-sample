package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceImpl;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceImpl entity.
 */
public interface EntityWithServiceImplSearchRepository extends ElasticsearchRepository<EntityWithServiceImpl, Long> {
}
