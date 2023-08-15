package com.davydenko.ordersWebApp;

import com.davydenko.ordersWebApp.controller.OrderController;
import com.davydenko.ordersWebApp.repository.OrderRepository;
import com.davydenko.ordersWebApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringJUnitConfig(OrdersWebAppApplicationTests.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testShowCreateOrderPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/create-order"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-order"));
    }

    @Test
    public void testCreateOrder() throws Exception {
        String firstName = "Іван";
        String lastName = "Іванов";
        String orderName = "Кофе";
        String orderDescription = "Description";

        mockMvc.perform(MockMvcRequestBuilders.post("/create-order")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("orderName", orderName)
                        .param("orderDescription", orderDescription))
                .andExpect(status().isOk())
                .andExpect(view().name("create-order"))
                .andExpect(model().attributeExists("message"));
    }
}
