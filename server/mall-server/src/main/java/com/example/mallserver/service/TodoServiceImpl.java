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
public class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;

	@Override
	public TodoDTO get(Long tno) {
		Optional<Todo> result = todoRepository.findById(tno);

		Todo todo = result.orElseThrow();

		return entityToDTO(todo);
	}

	@Override
	public Long register(TodoDTO todoDTO) {
		Todo todo = dtoToEntity(todoDTO); // 여기는 request 라 pk 가없고

		Todo result = todoRepository.save(todo); // 여기는 데이터베이스 처리된건기에 pk 가 있음

		return result.getTno();
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

		Todo todo = result.orElseThrow();
		todo.changeTitle(todoDTO.getTitle());
		todo.changeContent(todoDTO.getContent());
		todo.changeComplete(todoDTO.isComplete());
		todo.changeDueDate(todoDTO.getDueDate());

		todoRepository.save(todo);
	}

	@Override
	public void remove(Long tno) {
		todoRepository.deleteById(tno);
	}
}
