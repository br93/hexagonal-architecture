package com.food.ordering.system.domain.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.domain.dto.create.OrderAddress;
import com.food.ordering.system.domain.dto.create.OrderItemRequest;
import com.food.ordering.system.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.OrderItem;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderItemId;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.domain.valueobject.StreetAddress;

@Component
public class OrderMapper {

    public Order toOrder(CreateOrderRequest request) {
        return Order.builder()
                .customerId(new CustomerId(request.customerId()))
                .restaurantId(new RestaurantId(request.restaurantId()))
                .streetAddress(this.toStreetAddress(request.orderAddress()))
                .price(new Money(request.price()))
                .items(this.toListOrderItem(request.items()))
                .build();
    }

    public CreateOrderResponse toCreateOrderResponse(Order order) {
        return new CreateOrderResponse(order.getTrackingId().getValue(), order.getOrderStatus(), null);
    }

    public TrackOrderResponse toTrackOrderResponse(Order order) {
        return new TrackOrderResponse(order.getTrackingId().getValue(), order.getOrderStatus(),
                order.getFailureMessages());
    }

    private StreetAddress toStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(UUID.randomUUID(), orderAddress.street(), orderAddress.postalCode(),
                orderAddress.city());
    }

    private OrderItem toOrderItem(OrderItemRequest orderItemRequest, Long id) {

        return OrderItem.builder().id(new OrderItemId(id)).price(new Money(orderItemRequest.price()))
                .product(new Product(new ProductId(orderItemRequest.productId()))).quantity(orderItemRequest.quantity())
                .subtotal(new Money(orderItemRequest.subtotal())).build();
    }

    private List<OrderItem> toListOrderItem(List<OrderItemRequest> list) {
        var size = list.size();
        List<OrderItem> newList = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : list.reversed()) {
            newList.add(this.toOrderItem(orderItemRequest, Long.valueOf(size--)));
        }

        return newList.reversed();
    }
}
