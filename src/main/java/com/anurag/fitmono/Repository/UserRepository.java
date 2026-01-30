package com.anurag.fitmono.Repository;

import com.anurag.fitmono.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Boolean existsByEmail(String email);
}
