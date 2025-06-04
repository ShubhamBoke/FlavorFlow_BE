package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
