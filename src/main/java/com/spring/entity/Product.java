package com.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	 @NotBlank(message = "Name is required")
	 
        private String name;        // product name
	 
	 @NotBlank(message = "Name is required")
        private String description; // details
	 
	 @Min(value = 1, message = "Price must be greater than 0")
	    private double price;       // price
	 
	 @Min(value = 0, message = "Stock cannot be negative")  
	    private int stock;          // available quantity
	 
	    private String imageUrl;    // image link
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public Product(Long id, String name, String description, double price, int stock, String imageUrl) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.stock = stock;
			this.imageUrl = imageUrl;
		}
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
					+ ", stock=" + stock + ", imageUrl=" + imageUrl + "]";
		}


}
