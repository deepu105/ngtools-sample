package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Division;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Division entity.
 */
public interface DivisionSearchRepository extends ElasticsearchRepository<Division, Long> {
}
