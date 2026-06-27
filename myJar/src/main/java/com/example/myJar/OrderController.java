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
    public Orders create(@PathVariable int userId, @RequestBody Orders order) {

        return orderService.create(userId,order);
    }

    @GetMapping("/all")
    public List<Orders> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<Orders> getByUser(@PathVariable int userId) {
        return orderService.getByUserId(userId);
    }
}