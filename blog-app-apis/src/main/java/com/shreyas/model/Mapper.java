package com.shreyas.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shreyas.payloads.UserDto;

@Component
public class Mapper {
	
	@Autowired
	ModelMapper mapper;

	public User dtoToUser(UserDto dto) {
		User user = mapper.map(dto,User.class);
//		user.setUid(dto.getUid());
//		user.setName(dto.getName());
//		user.setPass(dto.getPass());
//		user.setEmail(dto.getEmail());
//		user.setAbout(dto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto dto = mapper.map(user, UserDto.class);
//		dto.setAbout(user.getAbout());
//		dto.setEmail(user.getEmail());
//		dto.setName(user.getName());
//		dto.setPass(user.getPass());
//		dto.setUid(user.getUid());
		
		return dto;
	}
}
