package com.example.FlavorFlow.Service;

import com.example.FlavorFlow.AuthConfig.JWTUtil;
import com.example.FlavorFlow.Enum.OrderStatus;
import com.example.FlavorFlow.Enum.Roles;
import com.example.FlavorFlow.Model.*;
import com.example.FlavorFlow.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    MealOrderRepository mealOrderRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public User registerUser(User user, Roles role) {
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
        Role roles = roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Default role not set up"));
        newUser.setRoles(Collections.singleton(roles));

        //Creating and saving a new cart
        Cart cart = cartRepository.save(new Cart());
        newUser.setCart(cart);
        return userRepository.save(newUser);
    }

    public MealOrder placeOrder(Long paymentMethodId, String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow(() -> new EntityNotFoundException("Unable to fetch Payment Method details with id: "+paymentMethodId));
        Cart cart = user.getCart();

        if (cart.isEnabled() && !cart.getCartItemList().isEmpty()) {
            //Create a new order and assign it to our user
            cart.setEnabled(false);
            cartRepository.save(cart);
            MealOrder mealOrder = new MealOrder();
            mealOrder.setCart(cart);
            mealOrder.setOrderStatus(OrderStatus.PLACED);
            mealOrder.setPaymentMethod(paymentMethod);
            user.getMealOrderList().add(mealOrderRepository.save(mealOrder));

            //Assign a new cart to user
            user.setCart(cartRepository.save(new Cart()));

            userRepository.save(user);
            return mealOrder;
        } else {
            throw new EntityNotFoundException("Cart is Empty");
        }
    }

    public List<MealOrder> getOrderList(String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));
        return user.getMealOrderList();
    }

    public MealOrder updateOrderStatus(Long mealOrderId, OrderStatus orderStatus, String jwt) {
        jwt = jwt.substring(7);
        User user = userRepository.findByUsername(jwtUtil.extractUsername(jwt)).orElseThrow(() -> new EntityNotFoundException("Unable to fetch user details"));
        MealOrder mealOrder = mealOrderRepository.findById(mealOrderId).orElseThrow(() -> new EntityNotFoundException("Unable to fetch Meal Order details with id: "+mealOrderId));
        //Confirm if the provided mealOrderId belongs to the user
        if (user.getMealOrderList().contains(mealOrder)) {
            //Confirm if the order status is 'PLACED'
            if (mealOrder.getOrderStatus().equals(OrderStatus.PLACED)) {
                mealOrder.setOrderStatus(orderStatus);
                return mealOrderRepository.save(mealOrder);
            } else {
                throw new RuntimeException("Cannot update the status as currently the status is: "+mealOrder.getOrderStatus().toString());
            }
        } else {
            throw new RuntimeException("Provided mealOrderId does not belongs to the User");
        }
    }

}
