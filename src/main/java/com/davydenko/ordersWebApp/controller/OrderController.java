package com.davydenko.ordersWebApp.controller;

import com.davydenko.ordersWebApp.model.Order;
import com.davydenko.ordersWebApp.model.OrderStatus;
import com.davydenko.ordersWebApp.model.User;
import com.davydenko.ordersWebApp.repository.OrderRepository;
import com.davydenko.ordersWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, SimpMessagingTemplate messagingTemplate, SimpMessagingTemplate messagingTemplate1) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate1;
    }

    @GetMapping("/create-order")
    public String showCreateOrderPage() {
        return "create-order";
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String orderName,
                              @RequestParam String orderDescription,
                              Model model) {
        User user = new User(firstName, lastName);
        userRepository.save(user);
        Order order = new Order(orderName, orderDescription);
        order.setStatus(OrderStatus.WAITING);
        orderRepository.save(order);

        messagingTemplate.convertAndSend("/topic/orders", order);

        model.addAttribute("message", "Замовлення " + orderName + " успішно створено");
        return "create-order";
    }
}

