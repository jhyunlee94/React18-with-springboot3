package com.example.mallserver.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.mallserver.domain.QTodo;
import com.example.mallserver.domain.Todo;
import com.example.mallserver.dto.PageRequestDTO;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

	public  TodoSearchImpl() {
		super(Todo.class);
	}
	@Override
	public Page<Todo> search1(PageRequestDTO pageRequestDTO) {
		// 부트에서 변경이 너무 많음
		// 옛 버전이랑 지금이랑 너무 다름
		log.info("search1................");

		QTodo todo = QTodo.todo;

		JPQLQuery<Todo> query = from(todo);

		// query.where(todo.title.contains("1"));// return type 이 predicate 예상한다 약간 True 값이라 생각,,

		// 스프링 3버전들어오면서 바뀐점
		Pageable pageable = PageRequest.of(
			pageRequestDTO.getPage() -1,
			pageRequestDTO.getSize(),
			Sort.by("tno").descending());
		this.getQuerydsl().applyPagination(pageable,query);

		List<Todo> list = query.fetch(); // 목록 데이터 가져올때

		long total = query.fetchCount(); // Long 타입

		return new PageImpl<>(list, pageable, total); // 페이징 처리 끝
	}
}
