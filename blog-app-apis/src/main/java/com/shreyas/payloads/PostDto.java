package com.shreyas.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private List<CommentDto> comments;
}
