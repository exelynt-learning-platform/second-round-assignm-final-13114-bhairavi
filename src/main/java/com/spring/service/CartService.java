package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.entity.Cart;
import com.spring.entity.Product;
import com.spring.entity.User;
import com.spring.repository.CartRepository;
import com.spring.repository.ProductRepository;
import com.spring.repository.UserRepository;

@Service
public class CartService {
	 @Autowired
	    private CartRepository cartRepo;

	    @Autowired
	    private ProductRepository productRepo;

	    @Autowired
	    private UserRepository userRepo;

	    // get logged user
	    private User getUser() {
	        String email = SecurityContextHolder.getContext().getAuthentication().getName();
	        return userRepo.findByEmail(email).orElseThrow();
	    }

	    //  add to cart
	    public Cart addToCart(Long productId, int quantity) {

	        User user = getUser();
	        Product product = productRepo.findById(productId).orElseThrow();
	        System.out.println("Add to cart: " + product.getName());
	        System.out.println(" User: " + user.getEmail());


	        Cart cart = new Cart();
	        cart.setUser(user);
	        cart.setProduct(product);
	        cart.setQuantity(quantity);

	        return cartRepo.save(cart);
	    }

	    // view cart
	    public List<Cart> getCart() {
	        return cartRepo.findByUser(getUser());
	    }

	    // remove
	    public void remove(Long id) {

	        Cart cart = cartRepo.findById(id).orElseThrow();

	        if (!cart.getUser().getEmail().equals(getUser().getEmail())) {
	            throw new RuntimeException("Unauthorized");
	        }

	        cartRepo.delete(cart);
	    }
	
}
