package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
