package com.shreyas.services;

import com.shreyas.payloads.CommentDto;

public interface CommentService {

	CommentDto addComment(CommentDto dto, Integer postId, Integer userId);
	void deleteComment(Integer commentId);
}
