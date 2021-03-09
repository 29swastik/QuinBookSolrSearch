package com.example.quinBooksolrSearch.controller;

import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    SearchService searchService;

    @GetMapping("/getUser/{userName}")
    public UserResponseDto getUsersList(@PathVariable String userName) {
        return searchService.getUsersList(userName);
    }

    @PostMapping("/addUser")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return searchService.addUser(userRequestDto);
    }


}
