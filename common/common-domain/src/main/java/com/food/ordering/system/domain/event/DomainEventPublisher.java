package com.food.ordering.system.domain.event;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
