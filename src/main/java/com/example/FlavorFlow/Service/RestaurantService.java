package com.example.FlavorFlow.Service;

import com.example.FlavorFlow.Model.MenuItem;
import com.example.FlavorFlow.Model.Restaurant;
import com.example.FlavorFlow.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        try {
            Restaurant newRestaurant = new Restaurant();

            newRestaurant.setCuisine(restaurant.getCuisine());
            newRestaurant.setName(restaurant.getName());
            newRestaurant.setRating(restaurant.getRating());
            newRestaurant.setIs_open(true);
            newRestaurant.setMenuItemList(new ArrayList<MenuItem>());

            return restaurantRepository.save(newRestaurant);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        try {
            return restaurantRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (Exception e) {
            return null;
        }

    }
}
