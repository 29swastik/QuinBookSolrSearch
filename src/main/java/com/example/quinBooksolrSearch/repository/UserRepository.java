package com.example.quinBooksolrSearch.repository;

import com.example.quinBooksolrSearch.entity.QuinBookUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<QuinBookUser, Long> {
}
