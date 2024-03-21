package com.example.mallserver.repository.search;

import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.ProductDTO;

public interface ProductSearch {

	PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO);
}
