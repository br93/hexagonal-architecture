package com.food.ordering.system.domain;

import java.util.List;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
    OrderPaidEvent payOrder(Order order);
    void approveOrder(Order order);
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);
    void cancelOrder(Order order, List<String> failureMessages);

    //POSSIBLE LAST STEPS DON'T RETURN EVENTS//
}
