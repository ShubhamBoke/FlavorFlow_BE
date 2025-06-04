package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
