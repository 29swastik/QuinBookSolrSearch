package com.example.quinBooksolrSearch.solr_repository;

import com.example.quinBooksolrSearch.entity.User;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

public interface UserSolrRepository extends SolrCrudRepository<User, Long> {
    User findByName(String searchTerm);
}
