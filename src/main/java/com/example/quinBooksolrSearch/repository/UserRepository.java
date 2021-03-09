package com.example.quinBooksolrSearch.repository;

import com.example.quinBooksolrSearch.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {
}
