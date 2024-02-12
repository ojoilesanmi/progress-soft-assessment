package com.progressoft.assessment.data.repository;

import com.progressoft.assessment.data.model.FXDeals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FXDealsRepository extends JpaRepository<FXDeals, Long> {
    boolean existsByUniqueDealId(String dealUniqueId);
}
