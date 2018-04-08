package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.SearchData;
import io.github.jhipster.application.repository.SearchDataRepository;
import io.github.jhipster.application.repository.search.SearchDataSearchRepository;
import io.github.jhipster.application.service.dto.SearchDataDTO;
import io.github.jhipster.application.service.mapper.SearchDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SearchData.
 */
@Service
@Transactional
public class SearchDataService {

    private final Logger log = LoggerFactory.getLogger(SearchDataService.class);

    private final SearchDataRepository searchDataRepository;

    private final SearchDataMapper searchDataMapper;

    private final SearchDataSearchRepository searchDataSearchRepository;

    public SearchDataService(SearchDataRepository searchDataRepository, SearchDataMapper searchDataMapper, SearchDataSearchRepository searchDataSearchRepository) {
        this.searchDataRepository = searchDataRepository;
        this.searchDataMapper = searchDataMapper;
        this.searchDataSearchRepository = searchDataSearchRepository;
    }

    /**
     * Save a searchData.
     *
     * @param searchDataDTO the entity to save
     * @return the persisted entity
     */
    public SearchDataDTO save(SearchDataDTO searchDataDTO) {
        log.debug("Request to save SearchData : {}", searchDataDTO);
        SearchData searchData = searchDataMapper.toEntity(searchDataDTO);
        searchData = searchDataRepository.save(searchData);
        SearchDataDTO result = searchDataMapper.toDto(searchData);
        searchDataSearchRepository.save(searchData);
        return result;
    }

    /**
     * Get all the searchData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SearchDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SearchData");
        return searchDataRepository.findAll(pageable)
            .map(searchDataMapper::toDto);
    }

    /**
     * Get one searchData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SearchDataDTO findOne(Long id) {
        log.debug("Request to get SearchData : {}", id);
        SearchData searchData = searchDataRepository.findOne(id);
        return searchDataMapper.toDto(searchData);
    }

    /**
     * Delete the searchData by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SearchData : {}", id);
        searchDataRepository.delete(id);
        searchDataSearchRepository.delete(id);
    }

    /**
     * Search for the searchData corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SearchDataDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SearchData for query {}", query);
        Page<SearchData> result = searchDataSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(searchDataMapper::toDto);
    }
}
