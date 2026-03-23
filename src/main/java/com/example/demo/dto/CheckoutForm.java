package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CheckoutForm {

	@NotBlank(message = "Full name is required")
	@Size(min = 2, max = 60, message = "Full name should be 2-60 characters")
	private String fullName;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Enter a valid 10-digit phone number")
	private String phone;

	@NotBlank(message = "Address is required")
	@Size(min = 5, max = 120, message = "Address should be 5-120 characters")
	private String address;

	@NotBlank(message = "City is required")
	@Size(min = 2, max = 50, message = "City should be 2-50 characters")
	private String city;

	@NotBlank(message = "Pincode is required")
	@Pattern(regexp = "^[0-9]{5,6}$", message = "Enter a valid pincode")
	private String pincode;

	@NotBlank(message = "Select a payment method")
	private String payment;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
}
