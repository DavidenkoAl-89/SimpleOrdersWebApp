package com.davydenko.ordersWebApp;

import com.davydenko.ordersWebApp.controller.AdminController;
import com.davydenko.ordersWebApp.model.Order;
import com.davydenko.ordersWebApp.repository.OrderRepository;
import com.davydenko.ordersWebApp.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig(OrdersWebAppApplicationTests.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void testViewAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Заказ 1", "Описание 1"));
        orders.add(new Order("Заказ 2", "Описание 2"));

        when(orderRepository.findAll()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attribute("orders", Matchers.hasSize(2)));
    }
}