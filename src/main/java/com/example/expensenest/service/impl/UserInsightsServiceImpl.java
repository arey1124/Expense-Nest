package com.example.expensenest.service.impl;

import com.example.expensenest.entity.UserInsightResponse;
import com.example.expensenest.entity.UserInsights;
import com.example.expensenest.repository.UserInsightsRepository;
import com.example.expensenest.repository.UserRepository;
import com.example.expensenest.service.UserInsightsService;
import org.springframework.stereotype.Service;

@Service
public class UserInsightsServiceImpl implements UserInsightsService {
    private UserInsightsRepository userInsightsRepository;

    public UserInsightsServiceImpl(UserInsightsRepository userInsightsRepository) {
        super();
        this.userInsightsRepository = userInsightsRepository;
    }
    @Override
    public UserInsightResponse getUserInsightResponse(int sellerId) {

        return userInsightsRepository.getUserInsightResponse(sellerId);
    }
}
