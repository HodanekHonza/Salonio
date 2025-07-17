package com.salonio.queue.internal;

import com.salonio.queue.QueueApi;
import com.salonio.queue.Queue;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
class QueueService implements QueueApi {

    private final ApplicationEventPublisher publisher;
    private final QueueRepository queueRepository;

    QueueService(ApplicationEventPublisher publisher, QueueRepository queueRepository) {
        this.publisher = publisher;
        this.queueRepository = queueRepository;
    }

    @Override
    public List<Queue> getBooking(String name) {
        final var foundQueue = queueRepository.findByName(name);
        publisher.publishEvent(foundQueue);
        return foundQueue;
    }

    @Override
    public List<Queue> createQueue(String name) {
        final var newQueue = new Queue(name);
        final var savedQueue = queueRepository.save(newQueue);
        publisher.publishEvent(savedQueue);
        return Collections.singletonList(savedQueue);
    }
}
