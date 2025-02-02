package com.shreyas.controllers;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.model.Category;
import com.shreyas.payloads.CategoryDto;
import com.shreyas.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/categories")
public class CategoryControllers {

	@Autowired
	private CategoryService service;
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCat(@RequestBody @Valid CategoryDto dto){
		return new ResponseEntity<CategoryDto>(service.createCategory(dto),HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCat(@RequestBody @Valid CategoryDto dto, @PathVariable Integer id){
		CategoryDto c = service.updateCategory(dto, id);
		return ResponseEntity.ok(c);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCat(@PathVariable Integer id){
		service.deleteCategory(id);
		return new ResponseEntity<>(Map.of("Message from server:","requested category deleted"),HttpStatus.OK);
	}
	
	//find one
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> findOne(@PathVariable Integer id){
		return ResponseEntity.ok(service.fetchCategory(id));
	}
	
	//findAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> findAll(){
		return new ResponseEntity<List<CategoryDto>>(service.fetchAll(),HttpStatus.OK);
	}
	
}
