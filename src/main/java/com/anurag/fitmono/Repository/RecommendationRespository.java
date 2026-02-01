package com.anurag.fitmono.Repository;

import com.anurag.fitmono.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRespository extends JpaRepository<Recommendation,String> {
}
