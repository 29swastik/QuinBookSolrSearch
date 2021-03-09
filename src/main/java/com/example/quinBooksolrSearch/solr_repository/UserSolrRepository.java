package com.example.quinBooksolrSearch.solr_repository;

import com.example.quinBooksolrSearch.entity.QuinBookUser;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface UserSolrRepository extends SolrCrudRepository<QuinBookUser, Long> {
    QuinBookUser findByUserName(Long id);
}
