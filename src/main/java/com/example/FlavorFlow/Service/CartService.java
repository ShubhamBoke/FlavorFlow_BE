package com.example.FlavorFlow.Service;

import com.example.FlavorFlow.AuthConfig.JWTUtil;
import com.example.FlavorFlow.Model.Cart;
import com.example.FlavorFlow.Model.CartItem;
import com.example.FlavorFlow.Model.MenuItem;
import com.example.FlavorFlow.Model.User;
import com.example.FlavorFlow.Repository.CartItemRepository;
import com.example.FlavorFlow.Repository.CartRepository;
import com.example.FlavorFlow.Repository.MenuItemRepository;
import com.example.FlavorFlow.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;


    public Cart addItemToCart(Long menuItemId, int quantity, String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new EntityNotFoundException("Unable to fetch Menu Item with id: "+menuItemId));
        Cart cart = user.getCart();

        //Creating cart item
        CartItem cartItem = new CartItem();
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(quantity);
        cartItem.setSubtotal((int)(menuItem.getPrice() * quantity));
        cartItem = cartItemRepository.save(cartItem);

        cart.getCartItemList().add(cartItem);
        cart.setTotal((int)(cart.getTotal() + cartItem.getSubtotal()));
        return cartRepository.save(cart);
    }

    public Cart getCartDetails(String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));

        return user.getCart();
    }

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity, String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));
        CartItem cartItem = user.getCart().getCartItemList().stream().filter(ci -> ci.getId().equals(cartItemId)).findFirst().orElseThrow(() -> new EntityNotFoundException("Unable to fetch cart item details for id: "+cartItemId));

        cartItem.setQuantity(quantity);
        cartItem.setSubtotal((int) (quantity * cartItem.getMenuItem().getPrice()));
        user.getCart().setTotal(calculateCartTotal(user));
        return cartItemRepository.save(cartItem);
    }

    public void removeCartItem(Long cartItemId, String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));
        //Check if cartItem belong in the users cartItem list.
        user.getCart().getCartItemList().stream().filter(ci -> ci.getId().equals(cartItemId)).findFirst().orElseThrow(() -> new EntityNotFoundException("Unable to fetch cart item details for id: "+cartItemId));

        user.getCart().getCartItemList().removeIf(ci -> ci.getId().equals(cartItemId));
        user.getCart().setTotal(calculateCartTotal(user));
        cartItemRepository.deleteById(cartItemId);
    }

    private int calculateCartTotal(User user) {
        int sum = 0;
        for (CartItem cartItem: user.getCart().getCartItemList()) {
            sum += cartItem.getSubtotal();
        }
        return sum;
    }
}
