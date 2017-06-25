package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.EntityWithServiceImplAndPagination;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EntityWithServiceImplAndPagination entity.
 */
public interface EntityWithServiceImplAndPaginationSearchRepository extends ElasticsearchRepository<EntityWithServiceImplAndPagination, Long> {
}
