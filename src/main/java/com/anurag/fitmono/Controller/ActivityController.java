package com.anurag.fitmono.Controller;

import com.anurag.fitmono.Services.ActivityService;
import com.anurag.fitmono.dto.ActivityRequest;
import com.anurag.fitmono.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivities(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityService.trackActivities(request));
    }
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader(value="X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
