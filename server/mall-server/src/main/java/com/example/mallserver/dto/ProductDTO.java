package com.example.mallserver.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	// 상품 하나에 이미지가 여러개 있으니까
	// DTO 는 내가 필요에 따라 많이 만들수 있다.

	// 2가지 용도
	// 상품 등록, 상품 조회
	// 등록할땐 multipart
	// 조회할땐 DBMS 에 파일을 저장하면 안됨 -> DB를 많이 씀 성능 안좋음 속도도 느려

	private Long pno;
	private String pname;
	private int price;
	private String pdesc;

	// 실제로 삭제는 하지않음 큰일남
	// 소프트 딜리트라고 이상한거 만들었는데 YN 처리함
	private boolean delFlag;
	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>(); // 진짜 파일

	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>(); // 조회용
}
