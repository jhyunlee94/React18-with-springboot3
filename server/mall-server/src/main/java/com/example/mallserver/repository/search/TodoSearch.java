package com.example.mallserver.repository.search;

import org.springframework.data.domain.Page;
import com.example.mallserver.domain.Todo;

public interface TodoSearch {

	Page<Todo> search1();
}
