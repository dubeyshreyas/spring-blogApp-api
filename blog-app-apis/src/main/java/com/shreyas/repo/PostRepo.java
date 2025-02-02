package com.shreyas.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shreyas.model.Category;
import com.shreyas.model.Post;
import com.shreyas.model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User u);
	List<Post> findByCategory(Category c);
}
