package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.global.GlobalData;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	CategoryService categoryService;

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
	
		
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart"; 
	}
	@GetMapping("/cart/removeItem/{index}")
	public String removeItem(@PathVariable int index)
	{
		GlobalData.cart.remove(index);
		return "redirect:/cart"; 
		
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}
}