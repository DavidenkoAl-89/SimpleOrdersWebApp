package com.davydenko.ordersWebApp.controller;

import com.davydenko.ordersWebApp.model.Order;
import com.davydenko.ordersWebApp.model.OrderStatus;
import com.davydenko.ordersWebApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private final OrderRepository orderRepository;
    @Autowired
    public AdminController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public String viewAllOrders(Model model) {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders/{id}")
    public String updateOrderStatus(@PathVariable Long id,
                                    @RequestParam OrderStatus status,
                                    Model model) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            String message;
            if (status == OrderStatus.ACCEPTED) {
                message = "Замовлення " + id + " прийнято";
            } else
                message = "Замовлення " + id + " відхилено";
            orderRepository.save(order);
            model.addAttribute("message", message);
        }
        List<Order> orders = (List<Order>) orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }
}
