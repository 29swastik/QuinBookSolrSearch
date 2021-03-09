package com.example.quinBooksolrSearch.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long userId;
    private String userName;
    private String email;
    private String location;
}
