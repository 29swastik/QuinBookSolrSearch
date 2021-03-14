package com.example.quinBooksolrSearch.service.impl;

import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.QuinBookUser;
import com.example.quinBooksolrSearch.repository.UserRepository;
import com.example.quinBooksolrSearch.service.SearchService;
import com.example.quinBooksolrSearch.solr_repository.UserSolrRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        Optional<QuinBookUser> quinBookUserOptional = userSolrRepository.findById(userRequestDto.getUserId());
        if(quinBookUserOptional.isPresent()) {
            userSolrRepository.deleteByUserId(userRequestDto.getUserId());
        }

        QuinBookUser quinBookUserFromDb = null;
        quinBookUserFromDb = userRepository.getByUserName(userRequestDto.getUserName());
        if(quinBookUserFromDb != null) {
            long id = quinBookUserFromDb.getUserId();
            BeanUtils.copyProperties(userRequestDto, quinBookUserFromDb);
            quinBookUserFromDb.setUserId(id);
            QuinBookUser savedQuinBookUser = userRepository.save(quinBookUserFromDb);
        }
        else {
            userRepository.save(quinBookUser);
        }
        QuinBookUser quinBookUser1 = userSolrRepository.save(quinBookUser);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(quinBookUser1, userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        return saveIntoSolr(userRequestDto);

    }

    @Override
    public List<UserResponseDto> getUsersListBasedOnString(String searchTerm) {
        PageRequest pageable = PageRequest.of(0, 5);

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<QuinBookUser> quinBookUserList = userSolrRepository.findByString(searchTerm, pageable);

        for(QuinBookUser quinBookUser: quinBookUserList) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(quinBookUser, userResponseDto);
            userResponseDto.setFullName(userResponseDto.getFirstName() + " " + userResponseDto.getLastName());
            userResponseDtoList.add(userResponseDto);
        }

        return userResponseDtoList;
    }


}
