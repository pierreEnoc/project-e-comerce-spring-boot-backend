package com.pierredev.projectjavaspringboot.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

	
}
