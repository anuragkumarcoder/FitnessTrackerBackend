package com.anurag.fitmono.dto;

import com.anurag.fitmono.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTimeAt;
    private LocalDateTime createdAt;
    private LocalDateTime updateTimeAt;
}
