package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Product;
import com.spring.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product add( @ Valid @RequestBody Product p) {
    	  System.out.println("👉 Product Added: " + p);
        return service.save(p);
    }

    @GetMapping
    public List<Product> get() {
        return service.getAll();
    }
}
