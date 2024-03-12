package com.food.ordering.system.domain;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.mapper.OrderMapper;
import com.food.ordering.system.domain.ports.output.publisher.OrderCreatedPaymentPublisher;

@Component
public class CreateOrderHandler {

    private final CreateOrderHelper createOrderHelper;
    private final OrderMapper orderMapper;
    private final OrderCreatedPaymentPublisher orderCreatedPaymentPublisher;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CreateOrderHandler(CreateOrderHelper createOrderHelper, OrderMapper orderMapper,
            OrderCreatedPaymentPublisher orderCreatedPaymentPublisher) {
        this.createOrderHelper = createOrderHelper;
        this.orderMapper = orderMapper;
        this.orderCreatedPaymentPublisher = orderCreatedPaymentPublisher;
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        OrderCreatedEvent orderCreatedEvent = this.createOrderHelper.persistOrder(request);
        Order order = orderCreatedEvent.getOrder();

        logger.info("Order created with id " + order.getId().getValue());
        this.orderCreatedPaymentPublisher.publish(orderCreatedEvent);
        return this.orderMapper.toCreateOrderResponse(order);
    }

}
