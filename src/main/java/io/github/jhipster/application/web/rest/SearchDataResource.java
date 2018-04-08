package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.SearchDataService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.SearchDataDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing SearchData.
 */
@RestController
@RequestMapping("/api")
public class SearchDataResource {

    private final Logger log = LoggerFactory.getLogger(SearchDataResource.class);

    private static final String ENTITY_NAME = "searchData";

    private final SearchDataService searchDataService;

    public SearchDataResource(SearchDataService searchDataService) {
        this.searchDataService = searchDataService;
    }

    /**
     * POST  /search-data : Create a new searchData.
     *
     * @param searchDataDTO the searchDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new searchDataDTO, or with status 400 (Bad Request) if the searchData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/search-data")
    @Timed
    public ResponseEntity<SearchDataDTO> createSearchData(@Valid @RequestBody SearchDataDTO searchDataDTO) throws URISyntaxException {
        log.debug("REST request to save SearchData : {}", searchDataDTO);
        if (searchDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new searchData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SearchDataDTO result = searchDataService.save(searchDataDTO);
        return ResponseEntity.created(new URI("/api/search-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /search-data : Updates an existing searchData.
     *
     * @param searchDataDTO the searchDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated searchDataDTO,
     * or with status 400 (Bad Request) if the searchDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the searchDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/search-data")
    @Timed
    public ResponseEntity<SearchDataDTO> updateSearchData(@Valid @RequestBody SearchDataDTO searchDataDTO) throws URISyntaxException {
        log.debug("REST request to update SearchData : {}", searchDataDTO);
        if (searchDataDTO.getId() == null) {
            return createSearchData(searchDataDTO);
        }
        SearchDataDTO result = searchDataService.save(searchDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, searchDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /search-data : get all the searchData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of searchData in body
     */
    @GetMapping("/search-data")
    @Timed
    public ResponseEntity<List<SearchDataDTO>> getAllSearchData(Pageable pageable) {
        log.debug("REST request to get a page of SearchData");
        Page<SearchDataDTO> page = searchDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/search-data");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /search-data/:id : get the "id" searchData.
     *
     * @param id the id of the searchDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the searchDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/search-data/{id}")
    @Timed
    public ResponseEntity<SearchDataDTO> getSearchData(@PathVariable Long id) {
        log.debug("REST request to get SearchData : {}", id);
        SearchDataDTO searchDataDTO = searchDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(searchDataDTO));
    }

    /**
     * DELETE  /search-data/:id : delete the "id" searchData.
     *
     * @param id the id of the searchDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/search-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteSearchData(@PathVariable Long id) {
        log.debug("REST request to delete SearchData : {}", id);
        searchDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/search-data?query=:query : search for the searchData corresponding
     * to the query.
     *
     * @param query the query of the searchData search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/search-data")
    @Timed
    public ResponseEntity<List<SearchDataDTO>> searchSearchData(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of SearchData for query {}", query);
        Page<SearchDataDTO> page = searchDataService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/search-data");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
