package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestInfiniteScrollEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestInfiniteScrollEntity entity.
 */
public interface FieldTestInfiniteScrollEntitySearchRepository extends ElasticsearchRepository<FieldTestInfiniteScrollEntity, Long> {
}
