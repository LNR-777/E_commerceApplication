package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	@RequestMapping({"/","/home"})
	public String home(Model model)
	{
		return "index";
	}
	
	
	@RequestMapping("/shop")
	public String shop(Model model)
	{
		
		model.addAttribute("categories",categoryService.dispCategory());
		model.addAttribute("products",productService.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		return "shop";
	}
	@RequestMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id,Model model)
	{
		model.addAttribute("categories",categoryService.dispCategory());
		model.addAttribute("products",productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		return "shop"; 
	}
	@RequestMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable long id,Model model)
	{
			
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		return "viewProduct"; 
	}
		
}