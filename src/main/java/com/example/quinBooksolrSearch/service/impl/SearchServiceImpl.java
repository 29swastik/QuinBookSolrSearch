package com.example.quinBooksolrSearch.service.impl;

import com.example.quinBooksolrSearch.dto.SearchHistoryRequestDto;
import com.example.quinBooksolrSearch.dto.SearchHistoryResponseDto;
import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.QuinBookUser;
import com.example.quinBooksolrSearch.entity.SearchHistory;
import com.example.quinBooksolrSearch.repository.SearchHistoryRepository;
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

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Override
    public UserResponseDto saveIntoSolr(UserRequestDto userRequestDto) {
        QuinBookUser quinBookUser = new QuinBookUser();
        BeanUtils.copyProperties(userRequestDto, quinBookUser);

        Optional<QuinBookUser> optionalQuinBookUser = userRepository.findById(userRequestDto.getUserId());
        if(optionalQuinBookUser.isPresent()) {
            userSolrRepository.deleteByUserId(userRequestDto.getUserId());
            userRepository.deleteById(userRequestDto.getUserId());
        }

        QuinBookUser quinBookUser1 = userSolrRepository.save(quinBookUser);
        userRepository.save(quinBookUser);

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
        List<QuinBookUser> quinBookUserList;
        try {
            quinBookUserList = userSolrRepository.findByString(searchTerm, pageable);
        }
        catch (NullPointerException e) {
            return null;

        }

        for(QuinBookUser quinBookUser: quinBookUserList) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(quinBookUser, userResponseDto);
            userResponseDto.setFullName(userResponseDto.getFirstName() + " " + userResponseDto.getLastName());
            userResponseDtoList.add(userResponseDto);
        }

        return userResponseDtoList;
    }

    @Override
    public SearchHistoryResponseDto userSearchHistory(SearchHistoryRequestDto searchHistoryRequestDto) {

        SearchHistoryResponseDto searchHistoryResponseDto = new SearchHistoryResponseDto();
        Optional<SearchHistory> searchHistory;
        try {
            searchHistory = searchHistoryRepository.findById(searchHistoryRequestDto.getUserId());
        }
        catch (NullPointerException e) {
            return null;
        }

        if(searchHistory.isPresent())
        BeanUtils.copyProperties(searchHistory.get(), searchHistoryResponseDto);

        return searchHistoryResponseDto;

    }

    @Override
    public SearchHistory saveHistory(SearchHistory searchHistory) {
        return searchHistoryRepository.save(searchHistory);
    }
}
