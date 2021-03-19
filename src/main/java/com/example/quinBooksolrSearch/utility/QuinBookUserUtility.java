package com.example.quinBooksolrSearch.utility;

import com.example.quinBooksolrSearch.entity.QuinBookUser;
import lombok.Data;

@Data
public class QuinBookUserUtility {

    public QuinBookUser quinBookUser;

    public QuinBookUser getQuinBookUser() {

        quinBookUser = new QuinBookUser();
        quinBookUser.setUserId(10);
        quinBookUser.setUserName("SwastikSwasti");
        quinBookUser.setFirstName("Swastik");
        quinBookUser.setLastName("K");
        quinBookUser.setImg("");
        quinBookUser.setRelationshipStatus("single");
        quinBookUser.setEducation10("Marimallappa");
        quinBookUser.setEducation12("Marimallappa");
        quinBookUser.setEducationUni("Jss");
        quinBookUser.setJobProfile("Intern");
        quinBookUser.setHobbies("Photography");
        quinBookUser.setCompanyName("QuinBay");
        quinBookUser.setYearsOfExp(1L);
        quinBookUser.setJobLocation("Bengaluru");
        quinBookUser.setAddress("#297 RamakrishnaNagar Mysuru");

        return quinBookUser;
    }


}
