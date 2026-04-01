package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.entity.Order;
import com.spring.service.OrderService;
import com.spring.service.PaymentService;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    // Create Payment using  (Stripe)
    @PostMapping("/create")
  
    public ResponseEntity<?> createPayment(@RequestParam Double amount) {
        try {
            PaymentIntent intent = paymentService.createPayment(amount);

            //  Return only needed fields
            Map<String, Object> response = new HashMap<>();
            response.put("id", intent.getId());
            response.put("clientSecret", intent.getClientSecret());
            response.put("amount", intent.getAmount());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment failed: " + e.getMessage());
        }
    }

    // gives payemnt sucees order gives
    @PostMapping("/success")
    public ResponseEntity<?> paymentSuccess(@RequestParam Long orderId) {
    	  System.out.println("Payment success for order: " + orderId);

        try {
            Order order = orderService.markAsPaid(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}