package com.myshop.demo.service;

import com.myshop.demo.domain.Order;

public interface OrderService {
    void saveOrder(Order order);
    Order getOrder(Long id);
}

