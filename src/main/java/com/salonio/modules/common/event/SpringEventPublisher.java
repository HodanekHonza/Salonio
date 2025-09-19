package com.salonio.modules.common.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@AllArgsConstructor
@Component
public class SpringEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher delegate;

    @Override
    public void publish(Object event) {
        delegate.publishEvent(event);
    }
}
