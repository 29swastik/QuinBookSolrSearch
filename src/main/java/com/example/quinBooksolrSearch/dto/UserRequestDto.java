package com.example.quinBooksolrSearch.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserRequestDto {

    private long userId;
    private String firstName;
    private String LastName;
    private String userName;
    private String img;
    private String relationshipStatus;
    private String education10;
    private String education12;
    private String educationUni;
    private String jobProfile;
    private String Hobbies;
    private java.sql.Date marriageAnniversary;
    private java.sql.Date quinbookJoinDate;
    private String companyName;
    private java.sql.Date jobStartDate;
    private java.sql.Date jobEndDate;
    private Long yearsOfExp;
    private String jobLocation;
    private String address;
}
