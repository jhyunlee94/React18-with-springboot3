package com.example.mallserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mallserver.domain.Todo;


public interface TodoRepository extends JpaRepository<Todo,Long> {
}
