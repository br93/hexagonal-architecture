package com.food.ordering.system.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderItemId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.domain.valueobject.StreetAddress;
import com.food.ordering.system.domain.valueobject.TrackingId;

public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress streetAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public void initializeOrder() {
        super.setId(new OrderId(super.newUUID()));
        this.trackingId = new TrackingId(super.newUUID());
        this.orderStatus = OrderStatus.PENDING;

        this.initializeOrderItems();
    }

    public void validateOrder() {
        this.validateInitialOrder();
        this.validateTotalPrice();
        this.validateItemsPrice();
    }

    public void pay() {
        if (this.orderStatus != OrderStatus.PENDING)
            throw new OrderDomainException("Order is not in correct state for pay operation");
        this.orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (this.orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for approve operation");
        this.orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (this.orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for init cancel operation");
        this.orderStatus = OrderStatus.CANCELLING;

        this.updateFailureMessages(failureMessages);
    }

    public void cancel() {
        if (this.orderStatus != OrderStatus.PENDING && this.orderStatus != OrderStatus.CANCELLING)
            throw new OrderDomainException("Order is not in correct state for cancel operation");
        this.orderStatus = OrderStatus.CANCELED;

        this.updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages){
        if (this.failureMessages != null && failureMessages != null)
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    // CHECK STATUS BEFORE INITIALIZATION //
    private void validateInitialOrder() {
        if (this.orderStatus != null || super.getId() != null)
            throw new OrderDomainException("Order is not in the correct state for initialization");

    }

    private void validateTotalPrice() {
        if (this.price == null || !this.price.isGreaterThanZero())
            throw new OrderDomainException("Total price must be greater than zero");

    }

    private void validateItemsPrice() {
        Money orderItemsTotal = this.items.stream().map(orderItem -> {
            this.validateItemPrice(orderItem);
            return orderItem.getSubtotal();
        }).reduce(Money.ZERO, Money::add);

        if (!this.price.equals(orderItemsTotal))
            throw new OrderDomainException("Total price: " + this.price.getAmount()
                    + " is not equal to Order items total: " + orderItemsTotal.getAmount());
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid())
            throw new OrderDomainException("Order item prize: " + orderItem.getPrice().getAmount()
                    + " is not valid for product " + orderItem.getProduct().getId().getValue());
    }

    private void initializeOrderItems() {
        long itemId = 1;

        for (OrderItem orderItem : items)
            orderItem.initializeOrderItems(super.getId(), new OrderItemId(itemId++));

    }

    private Order(Builder builder) {
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

    public static Builder builder() {
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

        public Order build() {
            return new Order(this);
        }

    }

}
