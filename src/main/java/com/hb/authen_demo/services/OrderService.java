package com.hb.authen_demo.services;

import com.hb.authen_demo.models.Order;
import com.hb.authen_demo.models.User;
import com.hb.authen_demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> findOrderByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
