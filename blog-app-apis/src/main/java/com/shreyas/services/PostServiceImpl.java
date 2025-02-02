package com.shreyas.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shreyas.exceptions.ResourceNotFound;
import com.shreyas.model.Category;
import com.shreyas.model.Post;
import com.shreyas.model.User;
import com.shreyas.payloads.PostDto;
import com.shreyas.payloads.PostResponse;
import com.shreyas.repo.CategoryRepo;
import com.shreyas.repo.PostRepo;
import com.shreyas.repo.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo pRepo;
	@Autowired
	UserRepo uRepo;
	@Autowired
	CategoryRepo cRepo;
	@Autowired
	ModelMapper mp;
	@Override
	public PostDto createPost(Integer userId, Integer catId, String title, String content) {
		// TODO Auto-generated method stub
		Post newPost = new Post();
		newPost.setAddedDate(new Date());
		newPost.setCategory(cRepo.findById(catId).orElseThrow(()->new ResourceNotFound("can't find this category while cretaing post")));
		newPost.setContent(content);
		newPost.setImageName("defaultImage");
		newPost.setTitle(title);
		newPost.setUser(uRepo.findById(userId).orElseThrow(()->new ResourceNotFound("can't find this user while cretaing post")));
		
		return mp.map(pRepo.save(newPost),PostDto.class);
	}

	@Override
	public PostDto updatePost(Integer id, PostDto dto) {
		// TODO Auto-generated method stub
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFound("no post found with this id"));
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());
		post.setTitle(dto.getTitle());
		
		return mp.map(pRepo.save(post), PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		// TODO Auto-generated method stub
		pRepo.deleteById(id);

	}

	@Override
	public PostDto getPost(Integer id) {
		// TODO Auto-generated method stub
		return mp.map(pRepo.findById(id).orElseThrow(()->new ResourceNotFound("no post found by this id")),PostDto.class);
	}

	@Override
	public PostResponse getAll(Integer pageNum, Integer pageSize, String sortby, String dir) {
		// TODO Auto-generated method stub
		Sort sorting = null;
		if(dir.equalsIgnoreCase("asc")) {
			sorting = Sort.by(sortby).ascending();
		}else if(dir.equalsIgnoreCase("desc")) {
			sorting = Sort.by(sortby).descending();
		}
		Pageable p = PageRequest.of(pageNum, pageSize,sorting);
		Page<Post> pagePost = pRepo.findAll(p);
		List<Post> posts = pagePost.getContent();
		List<PostDto> dtos =  posts.stream().map(post->mp.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setContent(dtos);
		response.setPageNum(pagePost.getNumber());
		response.setPageSize(pagePost.getSize());
		response.setTotalElements(pagePost.getTotalElements());
		response.setTotalPages(pagePost.getTotalPages());
		response.setLastPage(pagePost.isLast());
		return response;
	}

	//get by user id
	@Override
	public List<PostDto> getAllByUser(Integer userId) {
		// TODO Auto-generated method stub
		User usr = uRepo.findById(userId).orElseThrow(()->new ResourceNotFound("no user found by this id"));
		List<Post> posts = pRepo.findByUser(usr);
		return posts.stream().map(post->mp.map(post,PostDto.class)).collect(Collectors.toList());
	}

	//get by cat id
	@Override
	public List<PostDto> getAllByCategory(Integer catId) {
		// TODO Auto-generated method stub
		Category cat = cRepo.findById(catId).orElseThrow(()->new ResourceNotFound("no category found by this id"));
		List<Post> posts = pRepo.findByCategory(cat);
		return posts.stream().map(post->mp.map(post,PostDto.class)).collect(Collectors.toList());
	}

}
