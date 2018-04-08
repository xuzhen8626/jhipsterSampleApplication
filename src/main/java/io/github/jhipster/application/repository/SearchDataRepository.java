package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.SearchData;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SearchData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SearchDataRepository extends JpaRepository<SearchData, Long> {

}
