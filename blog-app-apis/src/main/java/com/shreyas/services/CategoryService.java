package com.shreyas.services;

import java.util.List;

import com.shreyas.payloads.CategoryDto;

public interface CategoryService {

	//create cat
	CategoryDto createCategory(CategoryDto dto);
	//update cat
	CategoryDto updateCategory(CategoryDto dto, Integer id);
	//delete cat
	void deleteCategory(Integer id);
	//get one
	CategoryDto fetchCategory(Integer id);
	//get all
	List<CategoryDto> fetchAll();
	
}
