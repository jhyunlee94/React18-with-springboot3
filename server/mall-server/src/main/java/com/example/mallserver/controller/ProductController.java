package com.example.mallserver.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mallserver.dto.ProductDTO;
import com.example.mallserver.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor // 외부 주입
@RequestMapping("/api/products")
public class ProductController {
	private final CustomFileUtil fileUtil; // 생성자 자동 주입

	@PostMapping("/")
	public Map<String, String> register(ProductDTO productDTO) {
		// 이게 json 데이터로 못받아요, 좀 골치아파져요
		// url 인코디 멀티파트 폼 데이터를 받을거라
		// 이렇게 처리를 할게요
		log.info("register: " +productDTO);

		// 파일 목록
		List<MultipartFile> files = productDTO.getFiles();

		List<String> uploadedFileNames = fileUtil.saveFiles(files);
		productDTO.setUploadFileNames(uploadedFileNames);

		log.info(uploadedFileNames);

		return Map.of("RESULT", "SUCCESS");
	}
}
