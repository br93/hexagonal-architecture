package com.food.ordering.system.domain;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.food.ordering.system.domain.ports.output.publisher.OrderCancelledPaymentPublisher;
import com.food.ordering.system.domain.ports.output.publisher.OrderCreatedPaymentPublisher;
import com.food.ordering.system.domain.ports.output.publisher.OrderPaidRestaurantPublisher;
import com.food.ordering.system.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.domain.ports.output.repository.RestaurantRepository;

@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class OrderTestConfigurationClass {

    /// BEANS OF MOCKED OUTPUT PORTS - PUBLISHERS AND REPOSITORIES///

    @Bean
    OrderCancelledPaymentPublisher orderCancelledPaymentPublisher(){
        return Mockito.mock(OrderCancelledPaymentPublisher.class);
    }

    @Bean
    OrderCreatedPaymentPublisher orderCreatePaymentPublisher(){
        return Mockito.mock(OrderCreatedPaymentPublisher.class);
    }

    @Bean
    OrderPaidRestaurantPublisher orderPaidRestaurantPublisher(){
        return Mockito.mock(OrderPaidRestaurantPublisher.class);
    }

    @Bean
    CustomerRepository customerRepository(){
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    OrderRepository orderRepository(){
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    RestaurantRepository restaurantRepository(){
        return Mockito.mock(RestaurantRepository.class);
    }

    /// BEANS OF DOMAIN CORE - ORDER DOMAIN SERVICE///

    @Bean
    OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }
    
    
}
