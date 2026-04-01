package com.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Cart {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;   

    @ManyToOne
    private Product product; 

    private int quantity; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart(Long id, User user, Product product, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}
    
    

}
