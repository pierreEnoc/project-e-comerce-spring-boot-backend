package com.pierredev.projectjavaspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pierredev.projectjavaspringboot.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
