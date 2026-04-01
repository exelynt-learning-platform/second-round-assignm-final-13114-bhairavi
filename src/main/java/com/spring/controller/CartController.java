package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spring.entity.Cart;
import com.spring.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService service;

    // add
    @PostMapping("/add")
    public Cart add(@RequestParam Long productId,
                    @RequestParam int quantity) {
        return service.addToCart(productId, quantity);
    }

    // view
    @GetMapping
    public List<Cart> get() {
        return service.getCart();
    }

    // delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}