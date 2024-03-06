package com.food.ordering.system.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.valueobject.StreetAddress;
import com.food.ordering.system.valueobject.TrackingId;

public class Order extends AggregateRoot<OrderId>{

    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress streetAddress;
    private final Money price;
    private final List<OrderItem> items;
    
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder){
        super.setId(builder.id);
        this.customerId = builder.customerId;
        this.restaurantId = builder.restaurantId;
        this.streetAddress = builder.streetAddress;
        this.price = builder.price;
        this.items = builder.items;
        this.trackingId = builder.trackingId;
        this.orderStatus = builder.orderStatus;
        this.failureMessages = builder.failureMessages;
    }

    public static Builder builder(){
        return new Builder();
    }
    
    public CustomerId getCustomerId() {
        return customerId;
    }
    public RestaurantId getRestaurantId() {
        return restaurantId;
    }
    public StreetAddress getStreetAddress() {
        return streetAddress;
    }
    public Money getPrice() {
        return price;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public TrackingId getTrackingId() {
        return trackingId;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {

    private OrderId id;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress streetAddress;
    private Money price;
    private List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Builder() {

    }

    public Builder id(OrderId id) {
        this.id = id;
        return this;
    }

    public Builder customerId(CustomerId customerId) {
        this.customerId = customerId;
        return this;
    }

    public Builder restaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public Builder streetAddress(StreetAddress streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public Builder price(Money price) {
        this.price = price;
        return this;
    }

    public Builder items(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public Builder trackingId(TrackingId trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public Builder orderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Builder failureMessages(List<String> failureMessages) {
        this.failureMessages = failureMessages;
        return this;
    }

    public Order build(){
        return new Order(this);
    }

    

    }

    
    
}
