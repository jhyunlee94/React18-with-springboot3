package com.example.mallserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mallserver.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@EntityGraph(attributePaths = "imageList") // 어떤애를 같이 로딩합니다.
	@Query("SELECT p from Product p where p.pno = :pno")
	Optional<Product> selectOne(@Param("pno") Long pno);

	@Modifying // update, delete 다 쓸수있음
	@Query("UPDATE Product p SET p.delFlag = :delFlag WHERE p.pno = :pno")
	void updateToDelete(@Param("pno") Long pno,@Param("delFlag") boolean flag);
}
