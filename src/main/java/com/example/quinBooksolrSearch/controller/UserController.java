package com.example.quinBooksolrSearch.controller;

import com.example.quinBooksolrSearch.dto.SearchHistoryRequestDto;
import com.example.quinBooksolrSearch.dto.SearchHistoryResponseDto;
import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.SearchHistory;
import com.example.quinBooksolrSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    SearchService searchService;

    @GetMapping("/getUserName/{searchTerm}")
    public List<UserResponseDto> getUsersListBasedOnString(@PathVariable("searchTerm") String searchTerm) {
        return searchService.getUsersListBasedOnString(searchTerm);
    }

    @PostMapping("/addUser")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return searchService.addUser(userRequestDto);
    }

    @PostMapping("/getHistory")
    public SearchHistoryResponseDto userSearchHistory(@RequestBody SearchHistoryRequestDto searchHistoryRequestDto) {
        return searchService.userSearchHistory(searchHistoryRequestDto);
    }

    @PostMapping("/saveHistory")
    public SearchHistory saveHistory(@RequestBody SearchHistory searchHistory) {
        return searchService.saveHistory(searchHistory);
    }

}
