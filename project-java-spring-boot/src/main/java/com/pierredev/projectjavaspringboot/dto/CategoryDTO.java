package com.pierredev.projectjavaspringboot.dto;

import com.pierredev.projectjavaspringboot.entities.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public CategoryDTO() {
		
	}

	public CategoryDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CategoryDTO(Category entity) {
		this.id= entity.getId();
		this.name=entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
