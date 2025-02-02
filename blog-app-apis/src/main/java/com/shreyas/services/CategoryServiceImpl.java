 package com.shreyas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.exceptions.ResourceNotFound;
import com.shreyas.model.Category;
import com.shreyas.payloads.CategoryDto;
import com.shreyas.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		// TODO Auto-generated method stub
		Category c =  repo.save(mapper.map(dto, Category.class));
		return mapper.map(c, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dto, Integer id) {
		// TODO Auto-generated method stub
		Category c = repo.findById(id).orElseThrow(()-> {return new ResourceNotFound("category not found");});
		c.setCat_name(dto.getCat_name());
		c.setCat_desc(dto.getCat_desc());
		return mapper.map(repo.save(c),CategoryDto.class);
	}
	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		Category c = repo.findById(id).orElseThrow(()-> new ResourceNotFound("requested category not found"));
		repo.deleteById(id);
	}

	@Override
	public CategoryDto fetchCategory(Integer id) {
		// TODO Auto-generated method stub
		Category c = repo.findById(id).orElseThrow(()-> new ResourceNotFound("requested category not found"));
		return mapper.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> fetchAll() {
		// TODO Auto-generated method stub
		List<Category> categories = repo.findAll();
		return categories.stream().map(cat->mapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
	}

}
