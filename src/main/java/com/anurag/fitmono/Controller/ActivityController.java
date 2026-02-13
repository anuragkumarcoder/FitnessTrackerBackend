package com.anurag.fitmono.Controller;

import com.anurag.fitmono.Services.ActivityService;
import com.anurag.fitmono.dto.ActivityRequest;
import com.anurag.fitmono.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivities(@RequestBody ActivityRequest request){
        // Automatically get the userId from the security context set by your filter
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(activityService.trackActivities(request, userId));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(){
        // Automatically get the userId from the security context
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}