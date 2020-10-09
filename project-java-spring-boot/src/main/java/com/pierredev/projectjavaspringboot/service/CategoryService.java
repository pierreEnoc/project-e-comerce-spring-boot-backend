package com.pierredev.projectjavaspringboot.service;

import java.util.Optional;


import com.pierredev.projectjavaspringboot.service.exceptions.EntityNotFoundException;
import com.pierredev.projectjavaspringboot.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pierredev.projectjavaspringboot.dto.CategoryDTO;
import com.pierredev.projectjavaspringboot.entities.Category;
import com.pierredev.projectjavaspringboot.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = categoryRepository.findAll(pageRequest);

		return list.map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity = categoryRepository.save(entity);
		entity.setName(dto.getName());

		return new CategoryDTO(entity);
	}
	@Transactional
	public CategoryDTO update(Long id,  CategoryDTO dto) {
		try {
			Category entity = categoryRepository.getOne(id);
			entity.setName(dto.getName());
			entity = categoryRepository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw  new ResourceNotFoundException("Id not found");
		}
	}

	
}
