package com.example.quinBooksolrSearch.service.impl;

import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.QuinBookUser;
import com.example.quinBooksolrSearch.repository.UserRepository;
import com.example.quinBooksolrSearch.service.SearchService;
import com.example.quinBooksolrSearch.solr_repository.UserSolrRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    UserSolrRepository userSolrRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto getUsersList(Long id) {

        Optional<QuinBookUser> quinBookUser = userSolrRepository.findById(id);
        UserResponseDto userResponseDto = new UserResponseDto();
        if(quinBookUser.isPresent()) {
            BeanUtils.copyProperties(quinBookUser.get(), userResponseDto);
        }
        return userResponseDto;
    }

    @Override
    public UserResponseDto saveIntoSolr(UserRequestDto userRequestDto) {
        QuinBookUser quinBookUser = new QuinBookUser();
        BeanUtils.copyProperties(userRequestDto, quinBookUser);
        QuinBookUser savedQuinBookUser = userRepository.save(quinBookUser);
        QuinBookUser quinBookUser1 = userSolrRepository.save(quinBookUser);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(quinBookUser1, userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        return saveIntoSolr(userRequestDto);

    }
}
