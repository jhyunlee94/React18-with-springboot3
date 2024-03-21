package com.example.mallserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mallserver.domain.Todo;
import com.example.mallserver.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}
