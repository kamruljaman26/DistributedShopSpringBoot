package com.shop.order.repository;

import com.shop.order.model.Order;
import com.shop.order.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
