package com.example.FlavorFlow.Controller;

import com.example.FlavorFlow.Model.MenuItem;
import com.example.FlavorFlow.Service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PostMapping("admin/addMenuItem")
    public ResponseEntity addMenuItems(@RequestBody MenuItem menuItem, @RequestParam("restaurantId") Long restaurantId) {
        try {
            return ResponseEntity.ok(menuItemService.addMenuItem(menuItem, restaurantId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getMenuItemListByRestaurantId")
    public ResponseEntity getMenuItemListByRestaurantId(@RequestParam("restaurantId") Long restaurantId) {
        try {
            return ResponseEntity.ok(menuItemService.getMenuItemListByRestaurantId(restaurantId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
