package com.example.quinBooksolrSearch.service.impl;

import com.example.quinBooksolrSearch.dto.SearchHistoryRequestDto;
import com.example.quinBooksolrSearch.dto.SearchHistoryResponseDto;
import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.dto.UserResponseDto;
import com.example.quinBooksolrSearch.entity.QuinBookUser;
import com.example.quinBooksolrSearch.entity.SearchHistory;
import com.example.quinBooksolrSearch.repository.SearchHistoryRepository;
import com.example.quinBooksolrSearch.repository.UserRepository;
import com.example.quinBooksolrSearch.solr_repository.UserSolrRepository;
import com.example.quinBooksolrSearch.utility.HistoryUtility;
import com.example.quinBooksolrSearch.utility.QuinBookUserUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
class SearchServiceImplTests {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private UserSolrRepository userSolrRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SearchHistoryRepository searchHistoryRepository;


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

        assertEquals(userResponseDtoList.size(), 2);

    }

    @Test
    public void saveIntoSolr() {

        QuinBookUserUtility quinBookUserUtility = new QuinBookUserUtility();
        QuinBookUser quinBookUser = quinBookUserUtility.getQuinBookUser();
        UserRequestDto userRequestDto = new UserRequestDto();
        BeanUtils.copyProperties(quinBookUser, userRequestDto);

        Mockito.when(userRepository.findById(10l)).thenReturn(Optional.of(quinBookUser));
        Mockito.when(userSolrRepository.deleteByUserId(10l)).thenReturn(1);
        Mockito.when(userSolrRepository.save(quinBookUser)).thenReturn(quinBookUser);
        Mockito.when(userRepository.save(quinBookUser)).thenReturn(quinBookUser);
        UserResponseDto userResponseDto = searchService.addUser(userRequestDto);

        assertEquals(userResponseDto.getFirstName(), "Swastik");
        Mockito.verify(userRepository).save(quinBookUser);

    }

    @Test
    public void testForNull() {

        PageRequest pageable = PageRequest.of(0, 5);

        Mockito.when(userSolrRepository.findByString("Arvind", pageable)).thenThrow(NullPointerException.class);
        Mockito.when(searchHistoryRepository.findById(10L)).thenThrow(NullPointerException.class);

        SearchHistoryRequestDto searchHistoryRequestDto = new SearchHistoryRequestDto();
        searchHistoryRequestDto.setUserId(10);
        searchHistoryRequestDto.setUserName("hemanth");

        SearchHistoryResponseDto searchHistoryResponseDto = searchService.userSearchHistory(searchHistoryRequestDto);

        List<UserResponseDto> userResponseDtoList =  searchService.getUsersListBasedOnString("Arvind");

        assertNull(searchHistoryResponseDto);
        assertNull(userResponseDtoList);

    }

    @Test
    public void testForHistorySave() {

        HistoryUtility historyUtility = new HistoryUtility();
        SearchHistory searchHistory = historyUtility.getSearchHistory();

        Mockito.when(searchHistoryRepository.save(searchHistory)).thenReturn(searchHistory);

        SearchHistory history = searchService.saveHistory(searchHistory);

        assertEquals(history.getHistory2(), "varun");

    }

    @Test
    public void searchHistory() {

        HistoryUtility historyUtility = new HistoryUtility();
        SearchHistory searchHistory = historyUtility.getSearchHistory();

        SearchHistoryRequestDto searchHistoryRequestDto = new SearchHistoryRequestDto();
        BeanUtils.copyProperties(searchHistory, searchHistoryRequestDto);

        Mockito.when(searchHistoryRepository.findById(10L)).thenReturn(Optional.of(searchHistory));
        SearchHistoryResponseDto searchHistoryResponseDto = searchService.userSearchHistory(searchHistoryRequestDto);

        assertEquals(searchHistoryResponseDto.getHistory2(), "varun");

    }


}
