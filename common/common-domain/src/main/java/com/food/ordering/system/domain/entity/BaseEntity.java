package com.food.ordering.system.domain.entity;

import java.util.UUID;

public abstract class BaseEntity<T> {

    private T id;

    public T getId(){
        return this.id;
    }

    public void setId(T id){
        this.id = id;
    }

    public UUID newUUID(){
        return UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity<?> other = (BaseEntity<?>) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



    
    
}
