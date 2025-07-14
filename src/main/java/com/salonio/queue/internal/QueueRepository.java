package com.salonio.queue.internal;

import com.salonio.queue.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface QueueRepository extends JpaRepository<Queue, Long> {
    List<Queue> findByName(String name);
}