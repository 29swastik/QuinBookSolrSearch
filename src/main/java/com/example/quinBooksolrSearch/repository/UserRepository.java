package com.example.quinBooksolrSearch.repository;

import com.example.quinBooksolrSearch.entity.QuinBookUser;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<QuinBookUser, Long> {

    @org.springframework.data.jpa.repository.Query(value = "select * from quin_book_user where user_name=?1", nativeQuery = true)
    QuinBookUser getByUserName(String userName);
}
