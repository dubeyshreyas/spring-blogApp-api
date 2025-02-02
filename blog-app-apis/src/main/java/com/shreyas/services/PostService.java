package com.shreyas.services;

import java.util.List;

import com.shreyas.model.Category;
import com.shreyas.model.User;
import com.shreyas.payloads.PostDto;
import com.shreyas.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(Integer userId, Integer catId, String title, String content);
	
	//update
	PostDto updatePost(Integer id, PostDto dto);
	
	//delete
	void deletePost(Integer id);
	
	//getOne
	
	PostDto getPost(Integer id);
	
	//getAll
	
	PostResponse getAll(Integer pageNum, Integer pageSize, String sortby, String dir);
	
	//getByUser
	
	List<PostDto> getAllByUser(Integer userId);
	
	//getByCategory
	
	List<PostDto> getAllByCategory(Integer catId);
}
