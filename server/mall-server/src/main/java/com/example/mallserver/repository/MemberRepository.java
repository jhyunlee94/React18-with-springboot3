package com.example.mallserver.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mallserver.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	@EntityGraph(attributePaths = {"memberRolesList"}) // 같이 가져오기 위해서 만약 여러개는 {}
	@Query("SELECT m from Member m WHERE m.email = :email")
	Member getWithRoles(@Param("email") String email);
}

