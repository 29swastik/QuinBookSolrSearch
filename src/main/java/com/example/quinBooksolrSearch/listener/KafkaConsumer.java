package com.example.quinBooksolrSearch.listener;

import com.example.quinBooksolrSearch.dto.UserRequestDto;
import com.example.quinBooksolrSearch.entity.QuinBookUser;
import com.example.quinBooksolrSearch.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    SearchService searchService;

    @KafkaListener(topics = "search", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        QuinBookUser quinBookUser = null;
        try {
            quinBookUser = objectMapper.readValue(message, QuinBookUser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        UserRequestDto userRequestDto = new UserRequestDto();
        BeanUtils.copyProperties(quinBookUser, userRequestDto);
        searchService.addUser(userRequestDto);
    }
}

