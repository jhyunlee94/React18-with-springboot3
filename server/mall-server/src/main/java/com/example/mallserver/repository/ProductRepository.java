package com.example.mallserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mallserver.domain.Product;
import com.example.mallserver.repository.search.ProductSearch;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {

	@EntityGraph(attributePaths = "imageList") // 어떤애를 같이 로딩합니다.
	@Query("SELECT p from Product p where p.pno = :pno")
	Optional<Product> selectOne(@Param("pno") Long pno);

	@Modifying // update, delete 다 쓸수있음
	@Query("UPDATE Product p SET p.delFlag = :delFlag WHERE p.pno = :pno")
	void updateToDelete(@Param("pno") Long pno, @Param("delFlag") boolean flag);

	// 엘리먼트 타입도 조인 가능, 그래서 Object
	@Query("SELECT p, pi FROM Product p left join p.imageList pi WHERE pi.ord = 0 AND p.delFlag = false")
	Page<Object[]> selectList(Pageable pageable);
}
