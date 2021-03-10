package com.example.quinBooksolrSearch.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private long userId;
    private String userName;
    private String img;
    private String relationshipStatus;
    private String education10;
    private String education12;
    private String educationUni;
    private String jobProfile;
    private String companyName;
    private java.sql.Date jobStartDate;
    private java.sql.Date jobEndDate;
    private Long yearsOfExp;
    private String jobLocation;
    private String address;
}
