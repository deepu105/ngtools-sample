package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestServiceClassEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestServiceClassEntity entity.
 */
public interface FieldTestServiceClassEntitySearchRepository extends ElasticsearchRepository<FieldTestServiceClassEntity, Long> {
}
