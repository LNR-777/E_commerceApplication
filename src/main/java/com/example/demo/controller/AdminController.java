package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;


@Controller
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+"/uploads/productImages";

	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;


	@RequestMapping("/admin")
	public String adminHome()
	{
		return "adminHome";
	}
	
	
	//Category Section
	
		@GetMapping("/admin/categories")
		public String getCat(Model model)
		{
			model.addAttribute("categories", categoryService.dispCategory());
			return "categories"; 
			
		}
		
		//add new category
		@RequestMapping("/admin/categories/add")
		public String addCategory(Model model)
		{
			model.addAttribute("category", new Category());
			
				return "addCategory"; 
		}

		
		
//		save category
		@RequestMapping("/admin/categories/save")
		public String saveCategory(@ModelAttribute("category") Category category)
		{
			
			categoryService.addCategory(category);
			return "redirect:/admin/categories";
		}


		//delete category
		@RequestMapping("/admin/categories/delete/{id}")
		public String delCategory(@PathVariable int id)
		{
			categoryService.delCategoryById(id);
			return "redirect:/admin/categories";
		}
		
		//update category
		@RequestMapping("/admin/categories/update/{id}")
		public String updateCategory(@PathVariable int id,Model model)
		{
			Optional<Category> category=categoryService.getCategoryById(id);
			if(category.isPresent())
			{
				model.addAttribute("category", category.get());
				return "addCategory";
			}
			else
				return "404";
		}

		
		//Product section
		@RequestMapping("/admin/product")
		public String product(Model model)
		{
			model.addAttribute("products",productService.getAllProduct());
			return "Product"; 
			}
		
		@RequestMapping("/admin/product/add")
		public String addProduct(Model model)
		{
				model.addAttribute("productDTO", new ProductDTO());
				model.addAttribute("categories",categoryService.dispCategory());
				return "addProduct"; 
		}
		
		//save product
		@RequestMapping("/admin/product/save")
	public String saveProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam(name = "imgUrl", required = false) String imgUrl,
			@RequestParam("imgName")String imgName)throws IOException
	{
				
			Product product=new Product();
			product.setProduct_id(productDTO.getProduct_id());
			product.setName(productDTO.getName());
			product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
				
			product.setPrice(productDTO.getPrice());
			product.setWeight(productDTO.getWeight());
		String imageUUID;
		String url = imgUrl != null ? imgUrl.trim() : "";
		if (!url.isEmpty()) {
			imageUUID = url;
		} else if(!file.isEmpty()) {
			String originalName = file.getOriginalFilename();
			String extension = "";
			if (originalName != null && originalName.contains(".")) {
				extension = originalName.substring(originalName.lastIndexOf("."));
			}
			imageUUID = UUID.randomUUID().toString() + extension;
			Path uploadPath = Paths.get(uploadDir);
			Files.createDirectories(uploadPath);
			Path fileNameAndPath=uploadPath.resolve(imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
			
		} else {
			imageUUID=imgName;
		}
			product.setImgName(imageUUID);			
			productService.addProduct(product);
						
			return "redirect:/admin/product";
		}

		//delete product
		@RequestMapping("/admin/product/delete/{id}")
		public String delProduct(@PathVariable long id)
		{
			productService.delProductById(id);
			return "redirect:/admin/product";
		}
		
		//update product
		@RequestMapping("/admin/product/update/{id}")
		public String updateProduct(@PathVariable long id,Model model)
		{
			ProductDTO productDTO=new ProductDTO();
			Product product=productService.getProductById(id).get();
			productDTO.setProduct_id(product.getProduct_id());
			productDTO.setName(product.getName());
			productDTO.setCategoryId(product.getCategory().getId());
			productDTO.setPrice(product.getPrice());
			productDTO.setWeight(product.getWeight());
			productDTO.setImgName(product.getImgName());
			
			model.addAttribute("categories",categoryService.dispCategory());
			model.addAttribute("productDTO",productDTO);
			return "addProduct";
		}
		
		
		//User
		
		
		@GetMapping("/admin/users")
		public String userList(Model model)
		{
			model.addAttribute("userdata",userService.dispUsers());
			return "users";
		}
		

		@GetMapping("/admin/registration")
		public String getRegistration()
		{
			return "registerAdmin";
		}
		
		
		@PostMapping("/admin/registration")
		public String saveUser(@ModelAttribute("user")UserDTO userDTO,Model model)
		{
			userDTO.setRole("ADMIN");
			userService.save(userDTO);
			model.addAttribute("message", "Registered Successfully");
			return "registerAdmin"; 
		}
		
		
		@RequestMapping("/admin/registration/delete/{id}")
		public String deleteUser(@PathVariable int id)
		{
			userService.deleteUserById(id);
			return "redirect:/admin/users";
		}




}
