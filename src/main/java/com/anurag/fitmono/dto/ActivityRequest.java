package com.anurag.fitmono.dto;

import com.anurag.fitmono.model.ActivityType;
import com.anurag.fitmono.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTimeAt;
}
