package com.anurag.fitmono.Services;

import com.anurag.fitmono.Repository.UserRepository;
import com.anurag.fitmono.dto.RegisterRequest;
import com.anurag.fitmono.dto.UserResponse;
import com.anurag.fitmono.model.User;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServices {
    //@Autowired
    private final UserRepository repo;
    public UserResponse register(RegisterRequest request) {
        if(repo.existsByEmail(request.getEmail())){
            throw new RuntimeException("user already exist");
        }
        // STEP 1: PREPARE (Map Request -> Entity)
        // We must do this because repo only understands 'User', not 'RegisterRequest'
        User user = new User();
        user.setFirstName(request.getFirstName()); // Get from REQUEST
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // (Ideally, encrypt this here!)

        // STEP 2: SAVE (The actual database operation)
        // This is the moment the ID and CreatedAt timestamps are generated
        User savedUser = repo.save(user);

        // STEP 3: RESPOND (Map Entity -> Response)
        // We do this to hide the password and formatting the data safely
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(savedUser.getEmail()); // Get from DB entity
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
         userResponse.setPassword(savedUser.getPassword());
         userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());


        return userResponse;
    }

    public  UserResponse getUserProfile(String userId) {
        var user=repo.findById(userId)
                .orElseThrow(()-> new RuntimeException("user does not exist"));
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail()); // Get from DB entity
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }
}
