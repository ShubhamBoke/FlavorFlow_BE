package com.example.FlavorFlow.Controller;

import com.example.FlavorFlow.Model.Restaurant;
import com.example.FlavorFlow.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/admin/addRestaurant")
    public ResponseEntity addRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurantService.addRestaurant(restaurant) != null) {
            return ResponseEntity.ok("Restaurant Added Successfully");
        }
        return new ResponseEntity("Failed to add Restaurant", HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/getRestaurantList")
    public ResponseEntity getRestaurantList() {
        List<Restaurant> restaurantList = restaurantService.getRestaurantList();
        if (restaurantList != null && restaurantList.size() > 0) {
            return ResponseEntity.ok(restaurantList);
        }
        return new ResponseEntity("Failed to fetch Restaurant list", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return new ResponseEntity("Failed to fetch Restaurant details", HttpStatus.NOT_FOUND);
    }
}
