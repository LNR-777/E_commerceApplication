package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void addCategory(Category category)
	{
		categoryRepository.save(category);
	}
	
	public List<Category>dispCategory()
	{
		return categoryRepository.findAll();
	 
	}
	public void delCategoryById(int id)
	{
		categoryRepository.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(int id)
	{
		return categoryRepository.findById(id);
	}

}