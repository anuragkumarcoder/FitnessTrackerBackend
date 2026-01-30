package com.anurag.fitmono.Services;

import com.anurag.fitmono.Repository.ActivityRepository;
import com.anurag.fitmono.Repository.UserRepository;
import com.anurag.fitmono.dto.ActivityRequest;
import com.anurag.fitmono.dto.ActivityResponse;
import com.anurag.fitmono.model.Activity;
import com.anurag.fitmono.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
private final ActivityRepository activityRepo;
private final UserRepository userRepo;

    public  ActivityResponse trackActivities(ActivityRequest request) {
        User user=userRepo.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("invalid user"+" "+request.getUserId()) );
        var activity=new Activity();
        activity.setType(request.getType());
        activity.setDuration(request.getDuration());
        activity.setCaloriesBurned(request.getCaloriesBurned());
        activity.setStartTimeAt(request.getStartTimeAt());
        activity.setAdditionalMetrics(request.getAdditionalMetrics());
        activity.setUser(user);

         Activity savedActivity= activityRepo.save(activity);
         ActivityResponse activityResponse=new ActivityResponse();
         activityResponse.setType(activity.getType());
         activityResponse.setDuration(activity.getDuration());
         activityResponse.setId(activity.getId());
         activityResponse.setUserId(activity.getUser().getId());
         activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
         activityResponse.setStartTimeAt(activity.getStartTimeAt());
         activityResponse.setCreatedAt(activity.getCreationTime());
         activityResponse.setUpdateTimeAt(activity.getUpdateTimeAt());
        activityResponse.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
        activityResponse.setCreatedAt(savedActivity.getCreationTime());
        activityResponse.setUpdateTimeAt(savedActivity.getUpdateTimeAt());
         return activityResponse;
    }
}
