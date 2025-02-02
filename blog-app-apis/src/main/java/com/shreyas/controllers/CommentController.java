package com.shreyas.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.payloads.CommentDto;
import com.shreyas.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@PostMapping("/add-comment")
	public ResponseEntity<CommentDto> createComment(
			@RequestParam(value = "User id") Integer u_id,
			@RequestParam(value = "Post id") Integer p_id,
			@RequestBody CommentDto content){
		
		CommentDto created =  service.addComment(content, p_id, u_id);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete-comment/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Integer id){
		service.deleteComment(id);
		return new ResponseEntity<>(Map.of("Comment deleted with comment id:",id),HttpStatus.OK);
	}

}
