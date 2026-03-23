package com.example.demo.dto;

import com.example.demo.model.Product;

public class CartItemDTO {
	private Product product;
	private int quantity;
	private double subtotal;
	private String categoryName;

	public CartItemDTO(Product product, int quantity, double subtotal, String categoryName) {
		this.product = product;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.categoryName = categoryName;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
}
