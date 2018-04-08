package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.SearchData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SearchData entity.
 */
public interface SearchDataSearchRepository extends ElasticsearchRepository<SearchData, Long> {
}
