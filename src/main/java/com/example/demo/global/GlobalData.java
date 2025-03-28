package com.example.demo.global;

import java.util.*;

import com.example.demo.model.Product;

public class GlobalData {
	public static List<Product>cart;
	static
	{
		cart=new ArrayList<Product>();
	}
}