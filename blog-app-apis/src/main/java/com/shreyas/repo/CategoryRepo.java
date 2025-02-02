package com.shreyas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shreyas.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
