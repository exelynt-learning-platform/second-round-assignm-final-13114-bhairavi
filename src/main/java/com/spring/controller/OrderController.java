package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Order;
import com.spring.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private OrderService service;

    @PostMapping("/create")
    public Order create() {
        return service.createOrder();
    }

    @GetMapping
    public List<Order> get() {
        return service.getOrders();
    }
}

