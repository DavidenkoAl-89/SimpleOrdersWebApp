package com.davydenko.ordersWebApp.config.websocket;

import com.davydenko.ordersWebApp.model.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class OrderWebSocketHandler {
    @MessageMapping("/create-order")
    @SendTo("/topic/orders")
    public Order handleNewOrder(Order order) {
        return order;
    }
}
