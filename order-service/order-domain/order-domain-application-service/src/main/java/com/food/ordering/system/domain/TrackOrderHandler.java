package com.food.ordering.system.domain;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.dto.track.TrackOrderRequest;
import com.food.ordering.system.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.exception.OrderNotFoundException;
import com.food.ordering.system.domain.mapper.OrderMapper;
import com.food.ordering.system.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.domain.valueobject.TrackingId;

@Component
public class TrackOrderHandler {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public TrackOrderHandler(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public TrackOrderResponse trackOrder(TrackOrderRequest request) {

        Order order = this.orderRepository.findByTrackingId(new TrackingId(request.orderTrackingId())).orElseThrow(
                () -> new OrderNotFoundException("Order with tracking id " + request.orderTrackingId() + " not found"));
        return this.orderMapper.toTrackOrderResponse(order);
    }

}
