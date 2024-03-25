package com.example.mallserver.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.ProductDTO;
import com.example.mallserver.service.ProductService;
import com.example.mallserver.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor // 외부 주입
@RequestMapping("/api/products")
public class ProductController {
	private final CustomFileUtil fileUtil; // 생성자 자동 주입

	private final ProductService productService;

	// @PostMapping("/")
	// public Map<String, String> register(ProductDTO productDTO) {
	// 	// 이게 json 데이터로 못받아요, 좀 골치아파져요
	// 	// url 인코디 멀티파트 폼 데이터를 받을거라
	// 	// 이렇게 처리를 할게요
	// 	log.info("register: " +productDTO);
	//
	// 	// 파일 목록
	// 	List<MultipartFile> files = productDTO.getFiles();
	//
	// 	List<String> uploadedFileNames = fileUtil.saveFiles(files);
	// 	productDTO.setUploadFileNames(uploadedFileNames);
	//
	// 	log.info(uploadedFileNames);
	//
	// 	return Map.of("RESULT", "SUCCESS");
	// }

	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGET(
		@PathVariable("fileName") String fileName
	) {
		return fileUtil.getFile(fileName);
	}

	@GetMapping("/list")
	public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {

		return productService.getList(pageRequestDTO);
	}

	@PostMapping("/")
	public Map<String, Long> register(ProductDTO productDTO) {
		// 파일 업로드가 이뤄줘야함
		// 먼저 해줘야함
		// 그래서
		List<MultipartFile> files = productDTO.getFiles();
		// 문자열 업로드 시켜줘야함
		List<String> uploadFileNames = fileUtil.saveFiles(files); // 문자열 목록 만들어 줌
		// 세팅해서 서비스에 주는겁니다.
		productDTO.setUploadFileNames(uploadFileNames); // 디비에 저장하기위해서 만든 이름

		log.info(uploadFileNames);

		Long pno = productService.register(productDTO);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return Map.of("RESULT", pno);
	}

	@GetMapping("/read/{pno}")
	public ProductDTO read(@PathVariable("pno") Long pno) {
		return productService.get(pno);
	}

	@PutMapping("/{pno}")
	public Map<String, String> modify(@PathVariable("pno") Long pno, ProductDTO productDTO) {
		// 업로드 저장 - 수정해서 업로드가되는 애들은 저장해야하는 애들
		// 기존 저장되고 데이터베이스에 존재
		// 화면에서 들어올때 새로 업로드된건 멀티파트로 오고
		// 지우고 싶지않은건 DTO 에 있고
		// 지우고 싶은 파일들은 거기서 빼야한다
		// 그래서 그거를 위해서 파일 삭제하는 기능을 만든거예요

		productDTO.setPno(pno);

		// old product 어떤 파일이 지워졌는지 알아내기 위해, DBMS 에 있는 파일(저장된)
		ProductDTO oldProductDTO = productService.get(pno);

		// file upload
		List<MultipartFile> files = productDTO.getFiles();
		List<String> currentUploadFileNames = fileUtil.saveFiles(files);

		// keep files, String
		List<String> uploadedFileNames = productDTO.getUploadFileNames();

		if (currentUploadFileNames != null && !currentUploadFileNames.isEmpty()) {
			uploadedFileNames.addAll(currentUploadFileNames);
		}

		productService.modify(productDTO);

		List<String> oldFileNames = oldProductDTO.getUploadFileNames();
		if (oldFileNames != null && oldFileNames.size() > 0) {

			List<String> removeFiles =
				oldFileNames.stream().filter((fileName) ->
					// 이미 이게 있다는 이야기
					// 저는 없는 애들만 남겨놔야하니까
					// 여기서 발견이 안되는거예요
					// uploadedFileNames.indexOf(fileName) > 0
					// 그래서 아래처럼
					uploadedFileNames.indexOf(fileName) == -1
				).collect(Collectors.toList());
			fileUtil.deleteFiles(removeFiles);
		} // end if

		return Map.of("RESULT", "SUCCESS");
	}

	@DeleteMapping("/{pno}")
	public Map<String, String> remove(@PathVariable("pno") Long pno) {
		List<String> oldFileNames = productService.get(pno).getUploadFileNames();

		productService.remove(pno);

		fileUtil.deleteFiles(oldFileNames);

		return Map.of("RESULT", "SUCCESS");
	}
}
