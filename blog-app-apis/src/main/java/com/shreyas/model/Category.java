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
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cat_id;
	
	@Column(name = "name")
	String cat_name;
	@Column(name = "description")
	String cat_desc;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	List<Post> posts = new ArrayList<>();
	
}
