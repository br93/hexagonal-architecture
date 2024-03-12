package com.food.ordering.system.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.domain.valueobject.CustomerId;

public interface CustomerRepository {

    Optional<Customer> findByCustomerId(CustomerId customerId);
    
}
