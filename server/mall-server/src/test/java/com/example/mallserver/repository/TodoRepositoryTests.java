package com.example.mallserver.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mallserver.domain.Todo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

	@Autowired
	private TodoRepository todoRepository;


	@Test
	public void test1() {
		Assertions.assertNotNull(todoRepository);

		log.info(todoRepository.getClass().getName());
	}

	@Test
	public void testInsert() {
		// given
		final Todo todo = Todo.builder()
			.title("Title")
			.content("Content")
			.dueDate(LocalDate.of(2023,12,30))
			.build();

		// when
		Todo result = todoRepository.save(todo);

		log.info(result);

		// then
		Assertions.assertEquals("Title",result.getTitle());
	}

	@Test
	public void testRead() {
		Long tno = 1L;

		Optional<Todo> result = todoRepository.findById(tno);

		Todo todo = result.orElseThrow();

		log.info(todo);
	}

	@Test
	public void testUpdate() {
		// 먼저 로딩하고 엔티티 객체를 변경 // setter
		Long tno = 1L;

		Optional<Todo> result = todoRepository.findById(tno);

		Todo todo = result.orElseThrow();

		todo.changeTitle("Updated Title");
		todo.changeContent("Updated Content");
		todo.changeComplete(true);

		todoRepository.save(todo);


	}
}
