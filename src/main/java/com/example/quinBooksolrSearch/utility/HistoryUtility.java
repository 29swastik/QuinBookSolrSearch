package com.example.quinBooksolrSearch.utility;

import com.example.quinBooksolrSearch.entity.SearchHistory;
import lombok.Data;

@Data
public class HistoryUtility {

    public SearchHistory searchHistory = null;

    public SearchHistory getSearchHistory() {

        searchHistory = new SearchHistory();

        searchHistory.setUserId(10);
        searchHistory.setUserName("swastik");
        searchHistory.setHistory1("arun");
        searchHistory.setHistory2("varun");
        searchHistory.setHistory3("tarun");
        searchHistory.setHistory4("sharan");
        searchHistory.setHistory5("karan");

        return searchHistory;

    }

}
