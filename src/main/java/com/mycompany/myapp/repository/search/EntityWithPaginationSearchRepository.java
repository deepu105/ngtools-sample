package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithPagination;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithPagination entity.
 */
public interface EntityWithPaginationSearchRepository extends ElasticsearchRepository<EntityWithPagination, Long> {
}
