package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceClassAndPagination;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceClassAndPagination entity.
 */
public interface EntityWithServiceClassAndPaginationSearchRepository extends ElasticsearchRepository<EntityWithServiceClassAndPagination, Long> {
}
