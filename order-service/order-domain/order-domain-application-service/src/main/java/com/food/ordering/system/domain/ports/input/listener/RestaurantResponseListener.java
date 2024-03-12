package com.food.ordering.system.domain.ports.input.listener;

import com.food.ordering.system.domain.dto.message.RestaurantApprovalResponse;

//SAGA ROLLBACK
public interface RestaurantResponseListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
    
}
