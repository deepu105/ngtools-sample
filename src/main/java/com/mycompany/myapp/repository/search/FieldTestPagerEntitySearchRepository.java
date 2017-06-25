package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.FieldTestPagerEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FieldTestPagerEntity entity.
 */
public interface FieldTestPagerEntitySearchRepository extends ElasticsearchRepository<FieldTestPagerEntity, Long> {
}
