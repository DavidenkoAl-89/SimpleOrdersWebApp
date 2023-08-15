package com.davydenko.ordersWebApp.repository;

import com.davydenko.ordersWebApp.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
}
