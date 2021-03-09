package com.example.quinBooksolrSearch.controller;

import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    SearchService searchService;

    @GetMapping("/getUser/{id}")
    public UserResponseDto getUsersList(@PathVariable("id") Long id) {
        return searchService.getUsersList(id);
    }

    @GetMapping("/getUserName/{searchTerm}")
    public List<UserResponseDto> getUsersListBasedOnString(@PathVariable("searchTerm") String searchTerm) {
        return searchService.getUsersListBasedOnString(searchTerm);
    }

    @PostMapping("/addUser")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return searchService.addUser(userRequestDto);
    }

}
