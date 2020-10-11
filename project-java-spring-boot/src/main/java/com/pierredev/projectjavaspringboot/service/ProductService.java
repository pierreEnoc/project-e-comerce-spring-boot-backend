package com.pierredev.projectjavaspringboot.service;

import com.pierredev.projectjavaspringboot.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pierredev.projectjavaspringboot.dto.ProductDTO;
import com.pierredev.projectjavaspringboot.entities.Product;
import com.pierredev.projectjavaspringboot.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = productRepository.findAll(pageRequest);
		
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findbyId(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new ProductDTO(entity, entity.getCategories());
	}
	
}
