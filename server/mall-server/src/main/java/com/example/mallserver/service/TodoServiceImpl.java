package com.example.mallserver.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mallserver.domain.Todo;
import com.example.mallserver.dto.TodoDTO;
import com.example.mallserver.repository.TodoRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

	private final TodoRepository todoRepository;
	@Override
	public TodoDTO get(Long tno) {
		Optional<Todo> result = todoRepository.findById(tno);

		Todo todo = result.orElseThrow();

		return entityToDTO(todo);
	}
}
