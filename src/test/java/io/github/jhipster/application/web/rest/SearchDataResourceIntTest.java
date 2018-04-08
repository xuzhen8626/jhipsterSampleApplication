package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.SearchData;
import io.github.jhipster.application.repository.SearchDataRepository;
import io.github.jhipster.application.service.SearchDataService;
import io.github.jhipster.application.repository.search.SearchDataSearchRepository;
import io.github.jhipster.application.service.dto.SearchDataDTO;
import io.github.jhipster.application.service.mapper.SearchDataMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.sameInstant;
import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.DataCategory;
/**
 * Test class for the SearchDataResource REST controller.
 *
 * @see SearchDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class SearchDataResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITY_ID = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_KUDOS_NUM = 9;
    private static final Integer UPDATED_KUDOS_NUM = 8;

    private static final Integer DEFAULT_COMMENT_NUM = 9;
    private static final Integer UPDATED_COMMENT_NUM = 8;

    private static final Integer DEFAULT_READ_NUM = 9;
    private static final Integer UPDATED_READ_NUM = 8;

    private static final Integer DEFAULT_COLLECTION_NUM = 9;
    private static final Integer UPDATED_COLLECTION_NUM = 8;

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final DataCategory DEFAULT_CATEGORY = DataCategory.TASK;
    private static final DataCategory UPDATED_CATEGORY = DataCategory.COMMENT;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_ORIGIN_DATA = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_DEL_FLAG = false;
    private static final Boolean UPDATED_DEL_FLAG = true;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    @Autowired
    private SearchDataRepository searchDataRepository;

    @Autowired
    private SearchDataMapper searchDataMapper;

    @Autowired
    private SearchDataService searchDataService;

    @Autowired
    private SearchDataSearchRepository searchDataSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSearchDataMockMvc;

    private SearchData searchData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SearchDataResource searchDataResource = new SearchDataResource(searchDataService);
        this.restSearchDataMockMvc = MockMvcBuilders.standaloneSetup(searchDataResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SearchData createEntity(EntityManager em) {
        SearchData searchData = new SearchData()
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .sourceType(DEFAULT_SOURCE_TYPE)
            .entityId(DEFAULT_ENTITY_ID)
            .kudosNum(DEFAULT_KUDOS_NUM)
            .commentNum(DEFAULT_COMMENT_NUM)
            .readNum(DEFAULT_READ_NUM)
            .collectionNum(DEFAULT_COLLECTION_NUM)
            .source(DEFAULT_SOURCE)
            .category(DEFAULT_CATEGORY)
            .status(DEFAULT_STATUS)
            .originData(DEFAULT_ORIGIN_DATA)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedTime(DEFAULT_LAST_MODIFIED_TIME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTime(DEFAULT_CREATED_TIME)
            .delFlag(DEFAULT_DEL_FLAG)
            .version(DEFAULT_VERSION)
            .tenantId(DEFAULT_TENANT_ID);
        return searchData;
    }

    @Before
    public void initTest() {
        searchDataSearchRepository.deleteAll();
        searchData = createEntity(em);
    }

    @Test
    @Transactional
    public void createSearchData() throws Exception {
        int databaseSizeBeforeCreate = searchDataRepository.findAll().size();

        // Create the SearchData
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);
        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isCreated());

        // Validate the SearchData in the database
        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeCreate + 1);
        SearchData testSearchData = searchDataList.get(searchDataList.size() - 1);
        assertThat(testSearchData.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSearchData.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testSearchData.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
        assertThat(testSearchData.getEntityId()).isEqualTo(DEFAULT_ENTITY_ID);
        assertThat(testSearchData.getKudosNum()).isEqualTo(DEFAULT_KUDOS_NUM);
        assertThat(testSearchData.getCommentNum()).isEqualTo(DEFAULT_COMMENT_NUM);
        assertThat(testSearchData.getReadNum()).isEqualTo(DEFAULT_READ_NUM);
        assertThat(testSearchData.getCollectionNum()).isEqualTo(DEFAULT_COLLECTION_NUM);
        assertThat(testSearchData.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testSearchData.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testSearchData.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSearchData.getOriginData()).isEqualTo(DEFAULT_ORIGIN_DATA);
        assertThat(testSearchData.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testSearchData.getLastModifiedTime()).isEqualTo(DEFAULT_LAST_MODIFIED_TIME);
        assertThat(testSearchData.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSearchData.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testSearchData.isDelFlag()).isEqualTo(DEFAULT_DEL_FLAG);
        assertThat(testSearchData.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testSearchData.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);

        // Validate the SearchData in Elasticsearch
        SearchData searchDataEs = searchDataSearchRepository.findOne(testSearchData.getId());
        assertThat(testSearchData.getLastModifiedTime()).isEqualTo(testSearchData.getLastModifiedTime());
        assertThat(testSearchData.getCreatedTime()).isEqualTo(testSearchData.getCreatedTime());
        assertThat(searchDataEs).isEqualToIgnoringGivenFields(testSearchData, "lastModifiedTime", "createdTime");
    }

    @Test
    @Transactional
    public void createSearchDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = searchDataRepository.findAll().size();

        // Create the SearchData with an existing ID
        searchData.setId(1L);
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SearchData in the database
        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = searchDataRepository.findAll().size();
        // set the field null
        searchData.setTitle(null);

        // Create the SearchData, which fails.
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKudosNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = searchDataRepository.findAll().size();
        // set the field null
        searchData.setKudosNum(null);

        // Create the SearchData, which fails.
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommentNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = searchDataRepository.findAll().size();
        // set the field null
        searchData.setCommentNum(null);

        // Create the SearchData, which fails.
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReadNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = searchDataRepository.findAll().size();
        // set the field null
        searchData.setReadNum(null);

        // Create the SearchData, which fails.
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCollectionNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = searchDataRepository.findAll().size();
        // set the field null
        searchData.setCollectionNum(null);

        // Create the SearchData, which fails.
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = searchDataRepository.findAll().size();
        // set the field null
        searchData.setCreatedBy(null);

        // Create the SearchData, which fails.
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        restSearchDataMockMvc.perform(post("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isBadRequest());

        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSearchData() throws Exception {
        // Initialize the database
        searchDataRepository.saveAndFlush(searchData);

        // Get all the searchDataList
        restSearchDataMockMvc.perform(get("/api/search-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(searchData.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].entityId").value(hasItem(DEFAULT_ENTITY_ID.toString())))
            .andExpect(jsonPath("$.[*].kudosNum").value(hasItem(DEFAULT_KUDOS_NUM)))
            .andExpect(jsonPath("$.[*].commentNum").value(hasItem(DEFAULT_COMMENT_NUM)))
            .andExpect(jsonPath("$.[*].readNum").value(hasItem(DEFAULT_READ_NUM)))
            .andExpect(jsonPath("$.[*].collectionNum").value(hasItem(DEFAULT_COLLECTION_NUM)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].originData").value(hasItem(DEFAULT_ORIGIN_DATA.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedTime").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED_TIME))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(sameInstant(DEFAULT_CREATED_TIME))))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID.toString())));
    }

    @Test
    @Transactional
    public void getSearchData() throws Exception {
        // Initialize the database
        searchDataRepository.saveAndFlush(searchData);

        // Get the searchData
        restSearchDataMockMvc.perform(get("/api/search-data/{id}", searchData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(searchData.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.sourceType").value(DEFAULT_SOURCE_TYPE.toString()))
            .andExpect(jsonPath("$.entityId").value(DEFAULT_ENTITY_ID.toString()))
            .andExpect(jsonPath("$.kudosNum").value(DEFAULT_KUDOS_NUM))
            .andExpect(jsonPath("$.commentNum").value(DEFAULT_COMMENT_NUM))
            .andExpect(jsonPath("$.readNum").value(DEFAULT_READ_NUM))
            .andExpect(jsonPath("$.collectionNum").value(DEFAULT_COLLECTION_NUM))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.originData").value(DEFAULT_ORIGIN_DATA.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedTime").value(sameInstant(DEFAULT_LAST_MODIFIED_TIME)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdTime").value(sameInstant(DEFAULT_CREATED_TIME)))
            .andExpect(jsonPath("$.delFlag").value(DEFAULT_DEL_FLAG.booleanValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.tenantId").value(DEFAULT_TENANT_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSearchData() throws Exception {
        // Get the searchData
        restSearchDataMockMvc.perform(get("/api/search-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSearchData() throws Exception {
        // Initialize the database
        searchDataRepository.saveAndFlush(searchData);
        searchDataSearchRepository.save(searchData);
        int databaseSizeBeforeUpdate = searchDataRepository.findAll().size();

        // Update the searchData
        SearchData updatedSearchData = searchDataRepository.findOne(searchData.getId());
        // Disconnect from session so that the updates on updatedSearchData are not directly saved in db
        em.detach(updatedSearchData);
        updatedSearchData
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .sourceType(UPDATED_SOURCE_TYPE)
            .entityId(UPDATED_ENTITY_ID)
            .kudosNum(UPDATED_KUDOS_NUM)
            .commentNum(UPDATED_COMMENT_NUM)
            .readNum(UPDATED_READ_NUM)
            .collectionNum(UPDATED_COLLECTION_NUM)
            .source(UPDATED_SOURCE)
            .category(UPDATED_CATEGORY)
            .status(UPDATED_STATUS)
            .originData(UPDATED_ORIGIN_DATA)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedTime(UPDATED_LAST_MODIFIED_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .delFlag(UPDATED_DEL_FLAG)
            .version(UPDATED_VERSION)
            .tenantId(UPDATED_TENANT_ID);
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(updatedSearchData);

        restSearchDataMockMvc.perform(put("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isOk());

        // Validate the SearchData in the database
        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeUpdate);
        SearchData testSearchData = searchDataList.get(searchDataList.size() - 1);
        assertThat(testSearchData.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSearchData.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSearchData.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
        assertThat(testSearchData.getEntityId()).isEqualTo(UPDATED_ENTITY_ID);
        assertThat(testSearchData.getKudosNum()).isEqualTo(UPDATED_KUDOS_NUM);
        assertThat(testSearchData.getCommentNum()).isEqualTo(UPDATED_COMMENT_NUM);
        assertThat(testSearchData.getReadNum()).isEqualTo(UPDATED_READ_NUM);
        assertThat(testSearchData.getCollectionNum()).isEqualTo(UPDATED_COLLECTION_NUM);
        assertThat(testSearchData.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testSearchData.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testSearchData.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSearchData.getOriginData()).isEqualTo(UPDATED_ORIGIN_DATA);
        assertThat(testSearchData.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testSearchData.getLastModifiedTime()).isEqualTo(UPDATED_LAST_MODIFIED_TIME);
        assertThat(testSearchData.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSearchData.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testSearchData.isDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
        assertThat(testSearchData.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testSearchData.getTenantId()).isEqualTo(UPDATED_TENANT_ID);

        // Validate the SearchData in Elasticsearch
        SearchData searchDataEs = searchDataSearchRepository.findOne(testSearchData.getId());
        assertThat(testSearchData.getLastModifiedTime()).isEqualTo(testSearchData.getLastModifiedTime());
        assertThat(testSearchData.getCreatedTime()).isEqualTo(testSearchData.getCreatedTime());
        assertThat(searchDataEs).isEqualToIgnoringGivenFields(testSearchData, "lastModifiedTime", "createdTime");
    }

    @Test
    @Transactional
    public void updateNonExistingSearchData() throws Exception {
        int databaseSizeBeforeUpdate = searchDataRepository.findAll().size();

        // Create the SearchData
        SearchDataDTO searchDataDTO = searchDataMapper.toDto(searchData);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSearchDataMockMvc.perform(put("/api/search-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(searchDataDTO)))
            .andExpect(status().isCreated());

        // Validate the SearchData in the database
        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSearchData() throws Exception {
        // Initialize the database
        searchDataRepository.saveAndFlush(searchData);
        searchDataSearchRepository.save(searchData);
        int databaseSizeBeforeDelete = searchDataRepository.findAll().size();

        // Get the searchData
        restSearchDataMockMvc.perform(delete("/api/search-data/{id}", searchData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean searchDataExistsInEs = searchDataSearchRepository.exists(searchData.getId());
        assertThat(searchDataExistsInEs).isFalse();

        // Validate the database is empty
        List<SearchData> searchDataList = searchDataRepository.findAll();
        assertThat(searchDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSearchData() throws Exception {
        // Initialize the database
        searchDataRepository.saveAndFlush(searchData);
        searchDataSearchRepository.save(searchData);

        // Search the searchData
        restSearchDataMockMvc.perform(get("/api/_search/search-data?query=id:" + searchData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(searchData.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].entityId").value(hasItem(DEFAULT_ENTITY_ID.toString())))
            .andExpect(jsonPath("$.[*].kudosNum").value(hasItem(DEFAULT_KUDOS_NUM)))
            .andExpect(jsonPath("$.[*].commentNum").value(hasItem(DEFAULT_COMMENT_NUM)))
            .andExpect(jsonPath("$.[*].readNum").value(hasItem(DEFAULT_READ_NUM)))
            .andExpect(jsonPath("$.[*].collectionNum").value(hasItem(DEFAULT_COLLECTION_NUM)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].originData").value(hasItem(DEFAULT_ORIGIN_DATA.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedTime").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED_TIME))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(sameInstant(DEFAULT_CREATED_TIME))))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].tenantId").value(hasItem(DEFAULT_TENANT_ID.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SearchData.class);
        SearchData searchData1 = new SearchData();
        searchData1.setId(1L);
        SearchData searchData2 = new SearchData();
        searchData2.setId(searchData1.getId());
        assertThat(searchData1).isEqualTo(searchData2);
        searchData2.setId(2L);
        assertThat(searchData1).isNotEqualTo(searchData2);
        searchData1.setId(null);
        assertThat(searchData1).isNotEqualTo(searchData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SearchDataDTO.class);
        SearchDataDTO searchDataDTO1 = new SearchDataDTO();
        searchDataDTO1.setId(1L);
        SearchDataDTO searchDataDTO2 = new SearchDataDTO();
        assertThat(searchDataDTO1).isNotEqualTo(searchDataDTO2);
        searchDataDTO2.setId(searchDataDTO1.getId());
        assertThat(searchDataDTO1).isEqualTo(searchDataDTO2);
        searchDataDTO2.setId(2L);
        assertThat(searchDataDTO1).isNotEqualTo(searchDataDTO2);
        searchDataDTO1.setId(null);
        assertThat(searchDataDTO1).isNotEqualTo(searchDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(searchDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(searchDataMapper.fromId(null)).isNull();
    }
}
