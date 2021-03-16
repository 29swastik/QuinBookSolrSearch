package com.example.quinBooksolrSearch.service.impl;

import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.QuinBookUser;
import com.example.quinBooksolrSearch.solr_repository.UserSolrRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
class SearchServiceImplTests {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private UserSolrRepository userSolrRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersListBasedOnString() throws IOException {

        PageRequest pageable = PageRequest.of(0, 5);
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> quinBookUserList = objectMapper.readValue(new URL("file:src/test/resources/users.mock"), ArrayList.class);


        List<QuinBookUser> quinBookUsers = new ArrayList<>();

        for(Map<String, Object> objectMap: quinBookUserList) {
            QuinBookUser quinBookUser = new QuinBookUser();
            quinBookUser.setUserId(Long.parseLong(objectMap.get("userId") + ""));
            quinBookUser.setUserName((String) objectMap.get("userName"));
            quinBookUser.setAddress((String) objectMap.get("address"));
            quinBookUser.setCompanyName((String) objectMap.get("companyName"));
            quinBookUser.setFirstName((String)objectMap.get("firstName"));
            quinBookUser.setLastName((String)objectMap.get("lastName"));
            quinBookUser.setJobEndDate((java.sql.Date) objectMap.get("jobEndDate"));
            quinBookUsers.add(quinBookUser);
        }

        Mockito.when(userSolrRepository.findByString("Kumar", pageable)).thenReturn(quinBookUsers);

        List<UserResponseDto> userResponseDtoList =  searchService.getUsersListBasedOnString("Kumar");

        assertEquals(userResponseDtoList.size(), 3);

    }

}
