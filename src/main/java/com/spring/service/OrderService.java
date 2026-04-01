package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.entity.Cart;
import com.spring.entity.Order;
import com.spring.entity.User;
import com.spring.repository.CartRepository;
import com.spring.repository.OrderRepository;
import com.spring.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    private User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email).orElseThrow();
    }

    // order create
    public Order createOrder() {

        User user = getUser();
        System.out.println("Creating order for: " + user.getEmail());
        List<Cart> cartList = cartRepo.findByUser(user);

        Order order = new Order();
        order.setUser(user);

        double total = 0;

        for (Cart c : cartList) {
            total += c.getProduct().getPrice() * c.getQuantity();
        }

        order.setProducts(cartList.stream().map(Cart::getProduct).toList());
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setShippingAddress("Default Address");

        cartRepo.deleteAll(cartList);

        return orderRepo.save(order);
    }

    // order view
    public List<Order> getOrders() {
        return orderRepo.findByUser(getUser());
    
    }

    // mathod payment sucess
    public Order markAsPaid(Long orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getEmail().equals(getUser().getEmail())) {
            throw new RuntimeException("Unauthorized");
        }

        order.setStatus("PAID");

        return orderRepo.save(order);
    }
}