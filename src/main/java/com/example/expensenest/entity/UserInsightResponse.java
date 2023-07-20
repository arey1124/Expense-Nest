package com.example.expensenest.entity;

import java.util.ArrayList;
import java.util.List;

public class UserInsightResponse {

    public UserInsightResponse(){
        userInsightsList = new ArrayList<UserInsights>();
    }
    private List<UserInsights> userInsightsList;

    public List<UserInsights> getUserInsightsList() {
        return userInsightsList;
    }

    public void setUserInsightsList(List<UserInsights> userInsightsList) {
        this.userInsightsList = userInsightsList;
    }
}
