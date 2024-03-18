package com.example.mallserver.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.mallserver.domain.Todo;
import com.example.mallserver.dto.TodoDTO;

@Transactional
public interface TodoService {
	// AOP 적용을할때를 위해 인터페이스 타입으로 빼주는게 좋음
	TodoDTO get(Long tno);
	default TodoDTO entityToDTO(Todo todo) {

		return TodoDTO.builder()
			.tno(todo.getTno())
			.title(todo.getTitle())
			.content(todo.getContent())
			.complete(todo.isComplete())
			.dueDate(todo.getDueDate())
			.build();
	}

	default Todo dtoToEntity(TodoDTO todoDTO) {

		return Todo.builder()
			.tno(todoDTO.getTno())
			.title(todoDTO.getTitle())
			.content(todoDTO.getContent())
			.complete(todoDTO.isComplete())
			.dueDate(todoDTO.getDueDate())
			.build();
	}
}
