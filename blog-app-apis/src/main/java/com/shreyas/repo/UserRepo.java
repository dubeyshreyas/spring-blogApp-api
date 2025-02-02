package com.shreyas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shreyas.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{ 

}
