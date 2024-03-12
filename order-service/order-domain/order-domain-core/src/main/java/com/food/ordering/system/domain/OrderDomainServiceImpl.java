package com.food.ordering.system.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.OrderItem;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.exception.DomainException;

public class OrderDomainServiceImpl implements OrderDomainService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String ORDER = "Order with id: ";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        this.validateRestaurant(restaurant);
        order.validateOrder();
        order.initializeOrder();

        var products = restaurant.getProducts();
        order.getItems().forEach(item -> validateProduct(item, products));

        logger.info(ORDER + order.getId().getValue() + " is initiated");
        return new OrderCreatedEvent(order, LocalDateTime.now());
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
       order.pay();
       logger.info(ORDER + order.getId().getValue() + " is paid");
       
       return new OrderPaidEvent(order, LocalDateTime.now());
    }

    /// CLIENT WON'T NEED AN EVENT, TRACKING VIA HTTP
    @Override
    public void approveOrder(Order order) {
       order.approve();
       logger.info(ORDER + order.getId().getValue() + " is approved");
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
       order.initCancel(failureMessages);
       logger.info(ORDER + order.getId().getValue() + " is cancelling");
    
       return new OrderCancelledEvent(order, LocalDateTime.now());
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        logger.info(ORDER + order.getId().getValue() + " is cancelled");
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive())
            throw new DomainException("Restaurant with id " + restaurant.getId() + " is not active");
    }

    private void validateProduct(OrderItem orderItem, List<Product> product) {

        List<UUID> products = product.stream().map(x -> x.getId().getValue()).toList();

        if (!products.contains(orderItem.getProduct().getId().getValue()))
            throw new DomainException("Product " + orderItem.getProduct().getId().getValue() + "from OrderItem "
                    + orderItem.getId().getValue() + "and Order " + orderItem.getOrderId().getValue() + " is invalid");
    }



}
