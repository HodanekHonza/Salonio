package com.salonio.queue.internal;

import com.salonio.queue.Queue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue")
class QueueController {

    private final QueueService queueService;

    QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/getQueue")
    List<Queue> getQueue(@RequestBody String name) {
        return queueService.getBooking(name);
    }

    @PostMapping("/createQueue")
    List<Queue> createQueue(@RequestBody String name) {
        return queueService.createQueue(name);
    }
}
