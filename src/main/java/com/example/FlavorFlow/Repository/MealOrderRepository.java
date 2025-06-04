package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Model.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealOrderRepository extends JpaRepository<MealOrder, Long> {
}
