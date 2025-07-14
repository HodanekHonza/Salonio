package com.salonio.queue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Queue {
    @Setter @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter @Getter
    @Column(name = "CUSTOMER_NAME", nullable = false, length = 99)
    private String name;

    public Queue(String name) {
        this.name = name;
    }

    public Queue() {

    }
}
