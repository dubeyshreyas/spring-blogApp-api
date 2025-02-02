package com.shreyas.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.exceptions.ResourceNotFound;
import com.shreyas.model.Comment;
import com.shreyas.model.Post;
import com.shreyas.model.User;
import com.shreyas.payloads.CommentDto;
import com.shreyas.repo.CommentRepo;
import com.shreyas.repo.PostRepo;
import com.shreyas.repo.UserRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	UserRepo u;
	@Autowired
	PostRepo p;
	@Autowired
	CommentRepo c;
	
	@Autowired
	ModelMapper mp;
	
	@Override
	public CommentDto addComment(CommentDto dto, Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		Post post = p.findById(postId).orElseThrow(()->new ResourceNotFound("could not find this post, sorry!"));
		User user = u.findById(userId).orElseThrow(()->new ResourceNotFound("could not find this user, sorry!"));
		Comment com = mp.map(dto, Comment.class);
		com.setPost(post);
		com.setUser(user);
		return mp.map(c.save(com),CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		c.deleteById(commentId);
	}

}
