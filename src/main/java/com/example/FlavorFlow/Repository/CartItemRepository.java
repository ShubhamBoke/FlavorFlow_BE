package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
