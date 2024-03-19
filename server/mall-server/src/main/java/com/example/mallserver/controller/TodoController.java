package com.example.mallserver.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/") // Long을 반환하기에 json을 위해서 Map으로 만들게요
	public Map<String, Long> register(
		@RequestBody TodoDTO dto
	) {
		log.info("todoDTO: " + dto);
		Long tno = todoService.register(dto); // return type Long
		return Map.of("TNO", tno);
	}

	@PutMapping("/{tno}")
	public Map<String, String> modify(
		@PathVariable("tno") Long tno,
		@RequestBody TodoDTO todoDTO) {

		todoDTO.setTno(tno);

		todoService.modify(todoDTO);

		return Map.of("RESULT", "SUCCESS");
	}

	@DeleteMapping("/{tno}")
	public Map<String, String> remove(@PathVariable("tno") Long tno) {
		todoService.remove(tno);
		return Map.of("RESULT", "SUCCESS");
	}
 }
