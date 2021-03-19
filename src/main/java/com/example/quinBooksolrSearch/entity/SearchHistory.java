package com.example.quinBooksolrSearch.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SearchHistory {

    @Id
    private long userId;
    private String UserName;
    private String history1;
    private String history2;
    private String history3;
    private String history4;
    private String history5;
}
