package com.davydenko.ordersWebApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_name")
    private String orderName;
    @Column(name = "description")
    private String orderDescription;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String orderName, String orderDescription) {
        this.orderName = orderName;
        this.orderDescription = orderDescription;
        this.user = user;
    }
}

