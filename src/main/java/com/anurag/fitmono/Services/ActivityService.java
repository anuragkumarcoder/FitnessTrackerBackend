package com.anurag.fitmono.Services;

import com.anurag.fitmono.Repository.ActivityRepository;
import com.anurag.fitmono.Repository.UserRepository;
import com.anurag.fitmono.dto.ActivityRequest;
import com.anurag.fitmono.dto.ActivityResponse;
import com.anurag.fitmono.model.Activity;
import com.anurag.fitmono.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepo;
    private final UserRepository userRepo;

    public ActivityResponse trackActivities(ActivityRequest request, String userId) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("invalid user " + request.getUserId()));

        Activity activity = new Activity();
        activity.setType(request.getType());
        activity.setDuration(request.getDuration());
        activity.setCaloriesBurned(request.getCaloriesBurned());
        activity.setStartTimeAt(request.getStartTimeAt());
        activity.setAdditionalMetrics(request.getAdditionalMetrics());
        activity.setUser(user);

        Activity savedActivity = activityRepo.save(activity);

        ActivityResponse response = new ActivityResponse();
        response.setId(savedActivity.getId());
        response.setUserId(savedActivity.getUser().getId());
        response.setType(savedActivity.getType());
        response.setDuration(savedActivity.getDuration());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setStartTimeAt(savedActivity.getStartTimeAt());
        response.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
        response.setCreatedAt(savedActivity.getCreationTime());
        response.setUpdateTimeAt(savedActivity.getUpdateTimeAt());

        return response;
    }

    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activityList = activityRepo.findByUserId(userId);
        List<ActivityResponse> responseList = new ArrayList<>();
        for (Activity activity : activityList) {
            ActivityResponse res = new ActivityResponse();
            res.setId(activity.getId());
            res.setUserId(activity.getUser().getId());
            res.setType(activity.getType());
            res.setDuration(activity.getDuration());
            res.setCaloriesBurned(activity.getCaloriesBurned());
            res.setStartTimeAt(activity.getStartTimeAt());
            res.setAdditionalMetrics(activity.getAdditionalMetrics());
            res.setCreatedAt(activity.getCreationTime());
            res.setUpdateTimeAt(activity.getUpdateTimeAt());
            responseList.add(res);
        }
        return responseList;
    }
}