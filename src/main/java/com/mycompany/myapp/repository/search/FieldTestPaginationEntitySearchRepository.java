package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestPaginationEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestPaginationEntity entity.
 */
public interface FieldTestPaginationEntitySearchRepository extends ElasticsearchRepository<FieldTestPaginationEntity, Long> {
}
