package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import com.example.demo.global.GlobalData;
import com.example.demo.model.Product;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.CheckoutForm;
import com.example.demo.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserDetailsService userDetailsService;
	

	@RequestMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) 
	{
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop"; 
		
	}
		
	
	@RequestMapping("/cart")
public String cartGet(Model model,Principal principal) 
	//public String cartGet(Model model)
	{
		UserDetails userDetails=userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);

		List<CartItemDTO> items = buildCartItems();
		double subtotal = items.stream().mapToDouble(CartItemDTO::getSubtotal).sum();
		double vat = 0.0;
		double total = subtotal + vat;
		
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("vat", vat);
		model.addAttribute("total", total);
		model.addAttribute("cartItems", items);
		model.addAttribute("cartEmpty", items.isEmpty());
		model.addAttribute("suggestions", productService.getAllProduct().stream().limit(4).toList());
		return "cart"; 
	}
	@GetMapping("/cart/removeItem/{index}")
	public String removeItem(@PathVariable int index)
	{
		GlobalData.cart.remove(index);
		return "redirect:/cart"; 
		
	}
	
	@GetMapping("/cart/increase/{id}")
	public String increaseQuantity(@PathVariable long id)
	{
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/cart";
	}
	
	@GetMapping("/cart/decrease/{id}")
	public String decreaseQuantity(@PathVariable long id)
	{
		for (int i = 0; i < GlobalData.cart.size(); i++) {
			if (GlobalData.cart.get(i).getProduct_id() == id) {
				GlobalData.cart.remove(i);
				break;
			}
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/cart/remove/{id}")
	public String removeAllOfItem(@PathVariable long id)
	{
		GlobalData.cart.removeIf(p -> p.getProduct_id() == id);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		List<CartItemDTO> items = buildCartItems();
		double subtotal = items.stream().mapToDouble(CartItemDTO::getSubtotal).sum();
		double vat = 0.0;
		double total = subtotal + vat;
		model.addAttribute("total", total);
		model.addAttribute("checkoutForm", new CheckoutForm());
		return "checkout";
	}

	@PostMapping("/checkout")
	public String placeOrder(@Valid CheckoutForm checkoutForm, BindingResult bindingResult, Model model)
	{
		List<CartItemDTO> items = buildCartItems();
		double subtotal = items.stream().mapToDouble(CartItemDTO::getSubtotal).sum();
		double vat = 0.0;
		double total = subtotal + vat;
		model.addAttribute("total", total);

		if (bindingResult.hasErrors()) {
			model.addAttribute("checkoutForm", checkoutForm);
			return "checkout";
		}

		model.addAttribute("orderSuccess", true);
		GlobalData.cart.clear();
		return "checkout";
	}
	
	private List<CartItemDTO> buildCartItems() {
		Map<Long, CartItemDTO> grouped = new LinkedHashMap<>();
		for (Product product : GlobalData.cart) {
			if (product == null) {
				continue;
			}
			long id = product.getProduct_id();
			CartItemDTO existing = grouped.get(id);
			String categoryName = "";
			try {
				if (product.getCategory() != null) {
					categoryName = product.getCategory().getName();
				}
			} catch (Exception ex) {
				categoryName = "";
			}
			if (existing == null) {
				grouped.put(id, new CartItemDTO(product, 1, product.getPrice(), categoryName));
			} else {
				int qty = existing.getQuantity() + 1;
				grouped.put(id, new CartItemDTO(product, qty, qty * product.getPrice(), categoryName));
			}
		}
		return new ArrayList<>(grouped.values());
	}
}
