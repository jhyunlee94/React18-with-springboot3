package com.example.mallserver.repository.search;

import org.springframework.data.domain.Page;

import com.example.mallserver.domain.Todo;
import com.example.mallserver.dto.PageRequestDTO;

public interface TodoSearch {

	Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
