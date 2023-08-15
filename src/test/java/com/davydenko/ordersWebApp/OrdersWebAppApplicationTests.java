package com.davydenko.ordersWebApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.davydenko.ordersWebApp")
class OrdersWebAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
