package com.example.mallserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.TodoDTO;
import com.example.mallserver.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

	private final TodoService todoService;

	@GetMapping("/{tno}")
	public TodoDTO get(@PathVariable("tno") Long tno){
		// 여기에 responseEntity 쓰는 사람있는데 이거는
		// advice 에서 빼주면 됩니다.
		// 기본적으로 json 이니 그것만 생각해주세요
		return todoService.get(tno);
	}
	/**
	 * pathvariable
	 * /api/todo/33  항상 동일한거
	 *
	 * querystring
	 * /list?page=3 이건 매일 달라지는것
	 */

	@GetMapping("/list")
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
		log.info("list....." + pageRequestDTO);

		return todoService.getList(pageRequestDTO);
	}
}
