package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestMapstructEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestMapstructEntity entity.
 */
public interface FieldTestMapstructEntitySearchRepository extends ElasticsearchRepository<FieldTestMapstructEntity, Long> {
}
