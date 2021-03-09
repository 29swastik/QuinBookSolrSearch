package com.example.quinBooksolrSearch.service.impl;

import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.User;
import com.example.quinBooksolrSearch.repository.UserRepository;
import com.example.quinBooksolrSearch.service.SearchService;
import com.example.quinBooksolrSearch.solr_repository.UserSolrRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    UserSolrRepository userSolrRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto getUsersList(String userName) {

        User user = userSolrRepository.findByName(userName);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);

        return userResponseDto;
    }

    @Override
    public void saveIntoSolr() {

        Iterable<User> userList = userRepository.findAll();
        userSolrRepository.saveAll(userList);
    }
}
