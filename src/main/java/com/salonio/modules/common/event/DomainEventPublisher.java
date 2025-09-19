package com.salonio.modules.common.event;

public interface DomainEventPublisher {
    void publish(Object event);
}
