package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Product;
import com.spring.repository.ProductRepository;

@Service
public class ProductService {
	 @Autowired
	    private ProductRepository repo;

	    public Product save(Product p) {
	        return repo.save(p);
	    }

	    public List<Product> getAll() {
	        return repo.findAll();
	    }
}
