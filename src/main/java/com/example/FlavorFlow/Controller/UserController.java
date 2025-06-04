package com.example.FlavorFlow.Controller;

import com.example.FlavorFlow.Enum.OrderStatus;
import com.example.FlavorFlow.Enum.Roles;
import com.example.FlavorFlow.Model.User;
import com.example.FlavorFlow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registerMember")
    public ResponseEntity registerMember(@RequestBody User user) {
        return registerUser(user, Roles.ROLE_MEMBER);
    }

    @PostMapping("/registerManager")
    public ResponseEntity registerManager(@RequestBody User user) {
        return registerUser(user, Roles.ROLE_MANAGER);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity registerAdmin(@RequestBody User user) {
        return registerUser(user, Roles.ROLE_ADMIN);
    }

    public ResponseEntity registerUser(@RequestBody User user, Roles role) {
        try {
            return ResponseEntity.ok(userService.registerUser(user, role));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }



    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestParam("paymentMethodId") Long paymentMethodId, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(userService.placeOrder(paymentMethodId, jwt));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getOrderList")
    public ResponseEntity getOrderList(@RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(userService.getOrderList(jwt));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/updateOrderStatus")
    public ResponseEntity updateOrderStatus(@RequestParam("mealOrderId") Long mealOrderId, @RequestParam("orderStatus") OrderStatus orderStatus, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(userService.updateOrderStatus(mealOrderId, orderStatus, jwt));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
