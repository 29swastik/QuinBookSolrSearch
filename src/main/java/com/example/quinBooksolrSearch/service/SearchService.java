package com.example.quinBooksolrSearch.service;

import com.example.quinBooksolrSearch.dto.SearchHistoryRequestDto;
import com.example.quinBooksolrSearch.dto.SearchHistoryResponseDto;
import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.SearchHistory;

import java.util.List;

public interface SearchService {

    UserResponseDto saveIntoSolr(UserRequestDto userRequestDto);

    UserResponseDto addUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getUsersListBasedOnString(String searchTerm);

    SearchHistoryResponseDto userSearchHistory(SearchHistoryRequestDto searchHistoryRequestDto);

    SearchHistory saveHistory(SearchHistory searchHistory);
}
