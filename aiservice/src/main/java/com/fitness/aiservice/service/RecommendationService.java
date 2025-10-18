package com.fitness.aiservice.service;


import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;


    public List<Recommendation> getUserRecommendation(String userId) {
     return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        int retries = 5;
        for (int i = 0; i < retries; i++) {
            Optional<Recommendation> rec = recommendationRepository.findByActivityId(activityId);
            if (rec.isPresent()) return rec.get();

            try {
                Thread.sleep(1000); // wait 1 sec
            } catch (InterruptedException ignored) {}
        }
        throw new RuntimeException("No RecommendationFound for this activity " + activityId);
    }
}
