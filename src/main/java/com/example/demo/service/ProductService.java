package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public List<Product>getAllProduct()
	{
		return productRepository.findAll();
	}
	
	public void addProduct(Product  product)
	{
		productRepository.save(product);
	}
	public void delProductById(Long id)
	{
		productRepository.deleteById(id);
	}

	public Optional <Product>getProductById(long id)
	{
		return productRepository.findById(id);
	}

	public List<Product>getAllProductsByCategoryId(int id)			//needs in user section
	{
		return productRepository.findAllByCategory_Id(id);
	}
}