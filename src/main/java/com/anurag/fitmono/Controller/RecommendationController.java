package com.anurag.fitmono.Controller;

import com.anurag.fitmono.Services.RecommendationService;
import com.anurag.fitmono.dto.RecommendedRequest;
import com.anurag.fitmono.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendedRequest request){
        Recommendation recommendation=recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

}
