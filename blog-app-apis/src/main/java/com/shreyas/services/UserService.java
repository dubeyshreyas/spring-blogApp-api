package com.shreyas.services;

import java.util.List;

import com.shreyas.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAll();
	void deleteById(Integer id);
}
