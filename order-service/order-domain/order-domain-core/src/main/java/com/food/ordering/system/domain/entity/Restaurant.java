package com.food.ordering.system.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.valueobject.RestaurantId;

public class Restaurant extends AggregateRoot<RestaurantId>{

    private final List<Product> products;
    private boolean active;
    
    private Restaurant(Builder builder) {
        super.setId(builder.id);
        this.products = builder.products;
        this.active = builder.active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private RestaurantId id;
        private List<Product> products;
        private boolean active;

        private Builder() {

        }

        public Builder id(RestaurantId id){
            this.id = id;
            return this;
        }


        public Builder products(List<Product> products){
            this.products = products;
            return this;
        }

        public Builder active(boolean active){
            this.active = active;
            return this;
        }

        public Restaurant build(){
            return new Restaurant(this);
        }

    }
}
