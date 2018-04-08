package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.SearchDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SearchData and its DTO SearchDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SearchDataMapper extends EntityMapper<SearchDataDTO, SearchData> {



    default SearchData fromId(Long id) {
        if (id == null) {
            return null;
        }
        SearchData searchData = new SearchData();
        searchData.setId(id);
        return searchData;
    }
}
