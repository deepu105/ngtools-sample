package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestServiceImplEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestServiceImplEntity entity.
 */
public interface FieldTestServiceImplEntitySearchRepository extends ElasticsearchRepository<FieldTestServiceImplEntity, Long> {
}
