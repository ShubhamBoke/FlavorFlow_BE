package com.example.FlavorFlow.Controller;

import com.example.FlavorFlow.Model.PaymentMethod;
import com.example.FlavorFlow.Service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @PostMapping("admin/addPaymentMethod")
    public ResponseEntity addPaymentMethod(@RequestParam("name") String name) {
        try {
            return ResponseEntity.ok(paymentMethodService.addPaymentMethod(name));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getPaymentMethodList")
    public ResponseEntity getPaymentMethodList() {
        try {
            return ResponseEntity.ok(paymentMethodService.getPaymentMethodList());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("admin/updatePaymentMethod")
    public ResponseEntity updatePaymentMethod(@RequestParam("paymentMethodId") Long paymentMethodId, @RequestBody PaymentMethod paymentMethod) {
        try {
            return ResponseEntity.ok(paymentMethodService.updatePaymentMethod(paymentMethodId, paymentMethod));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
