package com.spring.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "orders")
public class Order {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @ManyToOne
	    private User user;

	    @ManyToMany
	    private List<Product> products;

	    private double totalAmount;

	    private String status;
	    private String shippingAddress;

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

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Order(Long id, User user, List<Product> products, double totalAmount, String status) {
			super();
			this.id = id;
			this.user = user;
			this.products = products;
			this.totalAmount = totalAmount;
			this.status = status;
		}

		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", user=" + user + ", products=" + products + ", totalAmount=" + totalAmount
					+ ", status=" + status + "]";
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}
	    
}