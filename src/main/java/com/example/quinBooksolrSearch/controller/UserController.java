package com.example.quinBooksolrSearch.controller;

import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    SearchService searchService;

    @PostConstruct
    public void saveIntoSolr() {
        searchService.saveIntoSolr();
    }

    @GetMapping("/getUser/{userName}")
    public UserResponseDto getUsersList(@PathVariable String userName) {
        return searchService.getUsersList(userName);
    }


}
