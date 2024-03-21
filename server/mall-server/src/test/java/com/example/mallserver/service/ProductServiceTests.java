package com.example.mallserver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

	@Autowired
	private ProductService productService;

	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

		PageResponseDTO<ProductDTO> responseDTO = productService.getList(pageRequestDTO);

		log.info(responseDTO.getDtoList());
	}

}
