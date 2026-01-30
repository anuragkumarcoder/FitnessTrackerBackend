package com.anurag.fitmono.Repository;

import com.anurag.fitmono.model.Activity;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,String> {
}
