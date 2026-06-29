package com.example.myJar;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    private OrderDTO ConvertToDTO(Orders order) {
        return new OrderDTO(
                order.getId(),
                order.getItem(),
                order.getAmount(),
                order.getUser().getName()
        );
    }

    public OrderDTO create(int user_id, Orders orders) {
        User user = userRepository.findById(user_id).orElseThrow();
        if (user==null) {
            return null;
        }
        orders.setUser(user);
        Orders saved = orderRepository.save(orders);
        return ConvertToDTO(orders);
    }

    public List<OrderDTO> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::ConvertToDTO)
                .toList();

    }

    public List<OrderDTO> getByUserId(int user_id) {
        return orderRepository.findByUserId(user_id)
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }

}
