package com.example.FlavorFlow.Controller;

import com.example.FlavorFlow.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addItemToCart")
    public ResponseEntity addItemToCart(@RequestParam("menuItemId") Long menuItemId, @RequestParam("quantity") int quantity, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(cartService.addItemToCart(menuItemId, quantity, jwt));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getCartDetails")
    public ResponseEntity getCartDetails(@RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(cartService.getCartDetails(jwt));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/updateCartItemQuantity")
    public ResponseEntity updateCartItemQuantity(@RequestParam("cartItemId") Long cartItemId, @RequestParam("quantity") int quantity, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(cartService.updateCartItemQuantity(cartItemId, quantity, jwt));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/removeCartItem")
    public ResponseEntity removeCartItem(@RequestParam("cartItemId") Long cartItemId, @RequestHeader("Authorization") String jwt) {
        try {
            cartService.removeCartItem(cartItemId, jwt);
            return ResponseEntity.ok("Cart Item removed Successfully");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
