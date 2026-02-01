package com.anurag.fitmono.Services;

import com.anurag.fitmono.Repository.ActivityRepository;
import com.anurag.fitmono.Repository.RecommendationRespository;
import com.anurag.fitmono.Repository.UserRepository;
import com.anurag.fitmono.dto.RecommendedRequest;
import com.anurag.fitmono.model.Activity;
import com.anurag.fitmono.model.Recommendation;
import com.anurag.fitmono.model.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class RecommendationService {
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommendationRespository recommendationRepo;
    public Recommendation generateRecommendation(RecommendedRequest request){
        User user=userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("user not found"));
        Activity activity=activityRepository.findById(request.getActivityId())
                .orElseThrow(()->new RuntimeException("user not found"));
        Recommendation recommendation=Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

                Recommendation savedRecommendation=recommendationRepo.save(recommendation);
                return savedRecommendation;
    }
}
