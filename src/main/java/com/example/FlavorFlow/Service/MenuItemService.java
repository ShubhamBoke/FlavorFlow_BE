package com.example.FlavorFlow.Service;

import com.example.FlavorFlow.Model.MenuItem;
import com.example.FlavorFlow.Model.Restaurant;
import com.example.FlavorFlow.Repository.MenuItemRepository;
import com.example.FlavorFlow.Repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuItemRepository menuItemRepository;

    public MenuItem addMenuItem(MenuItem menuItem, Long restaurantId) {

        MenuItem newMenuItem = new MenuItem();
        newMenuItem.setName(menuItem.getName());
        newMenuItem.setRating(menuItem.getRating());
        newMenuItem.setPrice(menuItem.getPrice());
        newMenuItem = menuItemRepository.save(newMenuItem);

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException("Unable to fetch restaurant details with id: "+restaurantId));
        restaurant.getMenuItemList().add(newMenuItem);
        restaurantRepository.save(restaurant);
        return newMenuItem;
    }

    public List<MenuItem> getMenuItemListByRestaurantId(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException("Unable to fetch restaurant details with id: "+restaurantId));
        return restaurant.getMenuItemList();
    }
}
