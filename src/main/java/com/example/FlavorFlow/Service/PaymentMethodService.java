package com.example.FlavorFlow.Service;

import com.example.FlavorFlow.Model.PaymentMethod;
import com.example.FlavorFlow.Repository.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod addPaymentMethod(String name) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(name);
        return paymentMethodRepository.save(paymentMethod);
    }

    public List<PaymentMethod> getPaymentMethodList() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod updatePaymentMethod(Long paymentMethodId, PaymentMethod paymentMethod) {
        PaymentMethod currentPaymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow(() -> new EntityNotFoundException("Unable to fetch Payment details with id: "+paymentMethodId));
        currentPaymentMethod.setName(paymentMethod.getName());
        return paymentMethodRepository.save(currentPaymentMethod);
    }

}
