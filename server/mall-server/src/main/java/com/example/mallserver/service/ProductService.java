package com.example.mallserver.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.ProductDTO;

@Transactional
public interface ProductService {
	PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

	Long register(ProductDTO productDTO);
}
