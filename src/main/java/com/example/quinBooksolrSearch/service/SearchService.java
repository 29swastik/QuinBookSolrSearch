package com.example.quinBooksolrSearch.service;

import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;

import java.util.List;

public interface SearchService {
    UserResponseDto getUsersList(String searchTerm);

    UserResponseDto saveIntoSolr(UserRequestDto userRequestDto);

    UserResponseDto addUser(UserRequestDto userRequestDto);
}
