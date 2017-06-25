package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceClass;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceClass entity.
 */
public interface EntityWithServiceClassSearchRepository extends ElasticsearchRepository<EntityWithServiceClass, Long> {
}
