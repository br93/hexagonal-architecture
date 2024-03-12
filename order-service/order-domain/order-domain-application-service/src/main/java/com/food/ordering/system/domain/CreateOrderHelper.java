package com.food.ordering.system.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.OrderItemRequest;
import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.exception.DomainException;
import com.food.ordering.system.domain.mapper.OrderMapper;
import com.food.ordering.system.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.domain.ports.output.repository.RestaurantRepository;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.RestaurantId;

@Component
public class CreateOrderHelper {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderMapper orderMapper;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CreateOrderHelper(OrderDomainService orderDomainService, OrderRepository orderRepository,
            CustomerRepository customerRepository, RestaurantRepository restaurantRepository, OrderMapper orderMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderRequest request) {

        this.checkCustomer(request.customerId());
        Restaurant restaurant = checkRestaurant(request.restaurantId());

        this.checkProducts(request.items(), restaurant);

        request.items().forEach(x -> x.productId());
        Order order = this.orderMapper.toOrder(request);

        OrderCreatedEvent orderCreatedEvent = this.orderDomainService.validateAndInitiateOrder(order, restaurant);
        this.saveOrder(order);

        return orderCreatedEvent;

    }

    private void checkProducts(List<OrderItemRequest> items, Restaurant restaurant) {

        List<UUID> productsIds = items.stream().map(OrderItemRequest::productId).toList();
        List<UUID> productsRestaurant = restaurant.getProducts().stream().map(product -> product.getId().getValue())
                .toList();

        productsIds.forEach(id -> {
            if (!productsRestaurant.contains(id))
                throw new DomainException(
                        "Product with id " + id + " not found in restaurant " + restaurant.getId().getValue());

        });
    }

    private void checkCustomer(UUID customerId) {
        CustomerId id = new CustomerId(customerId);
        Optional<Customer> customer = customerRepository.findByCustomerId(id);

        if (!customer.isPresent())
            throw new DomainException("Customer with id " + customerId + " not found");

    }

    private Restaurant checkRestaurant(UUID restaurantId) {
        RestaurantId id = new RestaurantId(restaurantId);
        Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantId(id);

        return restaurant.orElseThrow(() -> new DomainException("Restaurant with id " + restaurantId + " not found"));
    }

    private void saveOrder(Order order) {
        Order savedOrder = this.orderRepository.save(order);

        if (order == null)
            throw new DomainException("Order could not be saved");

        logger.info("Order with id " + savedOrder.getCustomerId().getValue() + " saved");
    }

}
