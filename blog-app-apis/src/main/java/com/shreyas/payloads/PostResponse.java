package com.shreyas.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> content;
	private int pageNum;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	boolean lastPage ;
	

}
