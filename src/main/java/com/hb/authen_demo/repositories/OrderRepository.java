package com.hb.authen_demo.repositories;


import com.hb.authen_demo.models.Order;
import com.hb.authen_demo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    public List<Order> findByUser(User user);
}
