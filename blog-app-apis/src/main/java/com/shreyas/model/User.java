package com.shreyas.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

	@Column(length = 20, nullable = false)
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;
	@Column(length = 20, nullable = false)
	private String email;
	@Column(length = 500, nullable = false)
	private String pass;
	private String about;
	private String role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	List<Comment> comments = new ArrayList<>();
	
	
}
