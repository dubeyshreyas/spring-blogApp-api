package com.shreyas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.config.Constants;
import com.shreyas.payloads.PostDto;
import com.shreyas.payloads.PostResponse;
import com.shreyas.services.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	PostService service;
	
	//create
	@PostMapping("/create-new")
	public ResponseEntity<PostDto> createPost(@RequestParam Integer userId, @RequestParam Integer catId, @RequestParam String title, @RequestBody String content){
		
		PostDto post = service.createPost(userId, catId, title, content);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
		
	}
	//get all by userid
	@GetMapping("/get-all-user/{id}")
	public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Integer id) {
		return new ResponseEntity<List<PostDto>>(service.getAllByUser(id),HttpStatus.OK);
	}
	
	//get all by catid
		@GetMapping("/get-all-cat/{id}")
		public ResponseEntity<List<PostDto>> getAllPostsByCatId(@PathVariable Integer id) {
			return new ResponseEntity<List<PostDto>>(service.getAllByCategory(id),HttpStatus.OK);
		}
		
	//find one post by id
		@GetMapping("/{id}")
		public ResponseEntity<PostDto> findOne(@PathVariable Integer id){
			return ResponseEntity.ok(service.getPost(id));
		}
	//find all posts
		@GetMapping("/")
		public ResponseEntity<PostResponse> findAll(
				@RequestParam(value = "pageNum", defaultValue = Constants.PAGE_NUMBER, required = false) Integer Num,
				@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer Size,
				@RequestParam(defaultValue = Constants.SORT_BY, required = false) String sortBy,
				@RequestParam(defaultValue = Constants.SORT_DIR, required = false) String dir){
			return ResponseEntity.ok(service.getAll(Num,Size,sortBy,dir));
		}
		
	//update post
		@PutMapping("/update-post/{id}")
		public ResponseEntity<PostDto> updatePost(@PathVariable Integer id,@RequestBody PostDto dto){
			return ResponseEntity.ok(service.updatePost(id, dto));
		}
    //delete post
		@DeleteMapping("/delete-post/{id}")
		public ResponseEntity<?> deletePost(@PathVariable Integer id){
			service.deletePost(id);
			return new ResponseEntity<>(Map.of("post deleted by id: ",id),HttpStatus.OK);
		}
}
