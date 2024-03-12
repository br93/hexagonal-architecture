package com.food.ordering.system.domain;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.domain.dto.create.OrderAddress;
import com.food.ordering.system.domain.dto.create.OrderItemRequest;
import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.mapper.OrderMapper;
import com.food.ordering.system.domain.ports.input.service.OrderDomainApplicationService;
import com.food.ordering.system.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.domain.ports.output.repository.RestaurantRepository;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfigurationClass.class)
class OrderDomainApplicationServiceTest {

    @Autowired
    private OrderDomainApplicationService orderDomainApplicationService;

    @Autowired
    private OrderMapper orderMapper;

    /** WILL CHECK CONFIGURATION CLASS AND RETURN THE MOCK BEANS **/

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /** WILL CHECK CONFIGURATION CLASS AND RETURN THE MOCK BEANS **/

    private CreateOrderRequest createOrderRequestSuccess;
    private CreateOrderRequest createOrderRequestWrongPrice;
    private CreateOrderRequest createOrderRequestWrongProductPrice;

    private final UUID CUSTOMER_ID = UUID.fromString("09e9954a-794f-4926-a036-ae3e11a58e4d");
    private final UUID RESTAURANT_ID = UUID.fromString("1527c537-fd3c-49a8-8b39-8ef5b7628bae");
    private final UUID PRODUCT_ID = UUID.fromString("0af26d62-80e8-4df2-b3e0-a03f2b41fc62");
    private final UUID ORDER_ID = UUID.fromString("6ee6894e-acda-4ae5-9edc-52a16e2a02ed");

    private final String STREET = "street";
    private final String POSTAL_CODE = "00000-00";
    private final String CITY = "city";

    private final BigDecimal CORRECT_PRICE = new BigDecimal("200.00");
    private final BigDecimal WRONG_PRICE = new BigDecimal("250.00");

    @BeforeAll
    public void setup() {

        List<OrderItemRequest> listCorrectProductPrice = List
                .of(new OrderItemRequest(PRODUCT_ID, 1, new BigDecimal("50.00"), new BigDecimal("50.00")),
                        new OrderItemRequest(PRODUCT_ID, 3, new BigDecimal("50.00"), new BigDecimal("150.00")));

        List<OrderItemRequest> listWrongProductPrice = List
                .of(new OrderItemRequest(PRODUCT_ID, 1, new BigDecimal("60.00"), new BigDecimal("60.00")),
                        new OrderItemRequest(PRODUCT_ID, 3, new BigDecimal("50.00"), new BigDecimal("150.00")));

        createOrderRequestSuccess = this.mockCreateOrderRequest(CORRECT_PRICE, listCorrectProductPrice);
        createOrderRequestWrongPrice = this.mockCreateOrderRequest(WRONG_PRICE, listCorrectProductPrice);
        createOrderRequestWrongProductPrice = this.mockCreateOrderRequest(new BigDecimal("210.00"),
                listWrongProductPrice);

        List<Product> products = List.of(
                new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
                new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00"))));

        Restaurant restaurant = Restaurant.builder().id(new RestaurantId(RESTAURANT_ID)).products(products).active(true)
                .build();

        Customer customer = new Customer();
        customer.setId(new CustomerId(CUSTOMER_ID));

        Order order = this.orderMapper.toOrder(createOrderRequestSuccess);
        order.setId(new OrderId(ORDER_ID));

        Mockito.when(this.customerRepository.findByCustomerId(any(CustomerId.class))).thenReturn(Optional.of(customer));
        Mockito.when(this.restaurantRepository.findByRestaurantId(any(RestaurantId.class)))
                .thenReturn(Optional.of(restaurant));
        Mockito.when(this.orderRepository.save(any(Order.class))).thenReturn(order);
    }

    private CreateOrderRequest mockCreateOrderRequest(BigDecimal totalPrice, List<OrderItemRequest> items) {
        return new CreateOrderRequest(CUSTOMER_ID, RESTAURANT_ID, totalPrice, items,
                new OrderAddress(STREET, POSTAL_CODE, CITY));
    }

    @Test
    void testCreateOrder() {

        CreateOrderResponse response = this.orderDomainApplicationService.createOrder(createOrderRequestSuccess);

        Assertions.assertAll(
                () -> Assertions.assertEquals(OrderStatus.PENDING, response.orderStatus()),
                () -> Assertions.assertEquals("success", response.message()),
                () -> Assertions.assertNotNull(response.orderTrackingId()));

    }

}
