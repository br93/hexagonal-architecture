package com.food.ordering.system.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.valueobject.RestaurantId;

public interface RestaurantRepository {

    Optional<Restaurant> findByRestaurantId(RestaurantId restaurantId);
    
}
