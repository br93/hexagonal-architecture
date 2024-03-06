package com.food.ordering.system.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subtotal;

    private OrderItem(Builder builder){
        super.setId(builder.id);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subtotal = builder.subtotal;
    }

    public static Builder builder(){
        return new Builder();
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubtotal() {
        return subtotal;
    }

    public static final class Builder {
        private OrderItemId id;
        private Product product;
        private int quantity;
        private Money price;
        private Money subtotal;

        private Builder() {

        }
    
        public Builder id(OrderItemId id){
            this.id = id;
            return this;
        }

        public Builder product(Product product){
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder price(Money price){
            this.price = price;
            return this;
        }

        public Builder subtotal(Money subtotal){
            this.subtotal = subtotal;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

    

}
