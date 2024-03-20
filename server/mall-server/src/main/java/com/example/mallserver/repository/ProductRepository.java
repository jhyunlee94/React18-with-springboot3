package com.example.mallserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mallserver.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
