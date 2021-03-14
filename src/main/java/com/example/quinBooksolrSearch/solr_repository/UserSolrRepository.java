package com.example.quinBooksolrSearch.solr_repository;

import com.example.quinBooksolrSearch.entity.QuinBookUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface UserSolrRepository extends SolrCrudRepository<QuinBookUser, Long> {

    @Query("userName:*?0*")
    List<QuinBookUser> findByString(String searchTerm, PageRequest pageable);

    @Query("userName:?0")
    QuinBookUser findUserName(String userName);

    void deleteByUserId(long userId);
}

