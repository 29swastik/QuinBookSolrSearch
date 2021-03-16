package com.example.quinBooksolrSearch.dto;

import lombok.Data;

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

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", userName='" + userName + '\'' +
                ", img='" + img + '\'' +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                ", education10='" + education10 + '\'' +
                ", education12='" + education12 + '\'' +
                ", educationUni='" + educationUni + '\'' +
                ", jobProfile='" + jobProfile + '\'' +
                ", Hobbies='" + Hobbies + '\'' +
                ", marriageAnniversary=" + marriageAnniversary +
                ", quinbookJoinDate=" + quinbookJoinDate +
                ", companyName='" + companyName + '\'' +
                ", jobStartDate=" + jobStartDate +
                ", jobEndDate=" + jobEndDate +
                ", yearsOfExp=" + yearsOfExp +
                ", jobLocation='" + jobLocation + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
