package com.example.myJar;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository)
    {
        this.orderRepository = orderRepository;
        this.userRepository= userRepository;
    }

    public Orders create(int user_id, Orders orders)
    {
        User user = userRepository.findById(user_id).orElse(null);
        if (user == null)
        {
            return null;
        }
        orders.setUser(user);
        return orderRepository.save(orders);
    }

    public List<Orders> getAll()
    {
        return orderRepository.findAll();
    }

    public List<Orders> getByUserId(int user_id)
    {
        return orderRepository.findByUserId(user_id);
    }

}
