package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	List<Product>findAllByCategory_Id(int id);

}