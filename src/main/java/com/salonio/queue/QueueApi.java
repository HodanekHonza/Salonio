package com.salonio.queue;

import java.util.List;

public interface QueueApi {
    List<Queue> createQueue(String name);
    List<Queue> getBooking(String name);
}
