package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestEntity entity.
 */
public interface FieldTestEntitySearchRepository extends ElasticsearchRepository<FieldTestEntity, Long> {
}
