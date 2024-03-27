package com.example.mallserver.controller.advice;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mallserver.util.CustomJWTException;

@RestControllerAdvice
public class CustomControllerAdvice {
	// 공통으로 쓸려고 그리고 exception handler 만들어 줄려고 씁니다.

	@ExceptionHandler(NoSuchElementException.class)
	// 리턴 타입은 사실 상태코드를 만들어줘야하는데
	// 이를 세밀하게 하고싶으면 사실 ResponseEntity좋아요
	public ResponseEntity<?> notExist(NoSuchElementException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg", e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> notExist(MethodArgumentNotValidException e) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg", e.getMessage()));
	}

	@ExceptionHandler(CustomJWTException.class)
	public ResponseEntity<?> handleJWTException(CustomJWTException e) {
		String msg = e.getMessage();

		return ResponseEntity.ok().body(Map.of("error", msg));
	}
}
