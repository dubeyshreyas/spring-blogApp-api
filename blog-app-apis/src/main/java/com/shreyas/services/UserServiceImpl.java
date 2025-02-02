package com.shreyas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.exceptions.ResourceNotFound;
import com.shreyas.model.Mapper;
import com.shreyas.model.User;
import com.shreyas.payloads.UserDto;
import com.shreyas.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	Mapper mp;
	
	@Autowired
	UserRepo repo;

	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		User u = mp.dtoToUser(user);
		return mp.userToDto(repo.save(u));
	}

	@Override
	public UserDto updateUser(UserDto user, Integer id) {
		// TODO Auto-generated method stub
		User u = repo.findById(id).orElseThrow(()-> new ResourceNotFound("user not found"));
		u.setAbout(user.getAbout());
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPass(user.getPass());
		return mp.userToDto(repo.save(u));
		
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		User u = repo.findById(id).orElseThrow(()-> new ResourceNotFound("user not found"));
		return mp.userToDto(u);
	}

	@Override
	public List<UserDto> getAll() {
		// TODO Auto-generated method stub
		List<User> users = repo.findAll();
		List<UserDto> dtos =  users.stream().map(user->mp.userToDto(user)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		User u =  repo.findById(id).orElseThrow(()-> new ResourceNotFound("user doesn't exist"));
		repo.delete(u);

	}

}
