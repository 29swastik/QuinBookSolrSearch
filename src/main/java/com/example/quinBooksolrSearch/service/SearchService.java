package com.example.quinBooksolrSearch.service;

import com.example.quinBooksolrSearch.dto.UserResponseDto;

import java.util.List;

public interface SearchService {
    UserResponseDto getUsersList(String searchTerm);

    void saveIntoSolr();
}
