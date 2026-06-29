package com.example.myJar;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

   private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService=orderService;
    }

    @PostMapping("/add/{userId}")
    public OrderDTO create(@PathVariable int userId, @RequestBody Orders order) {

        return orderService.create(userId,order);
    }

    @GetMapping("/all")
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getByUser(@PathVariable int userId) {
        return orderService.getByUserId(userId);
    }
}