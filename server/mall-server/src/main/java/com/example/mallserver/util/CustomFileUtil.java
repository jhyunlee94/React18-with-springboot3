package com.example.mallserver.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor // 의존성 주입을 위한 설정
public class CustomFileUtil {
	// 원래는 인터페이스 만들어서 빼주는게 좋아요
	// 하지만 그냥 할게요

	@Value("${com.example.upload.path}") // 롬복이 아닌 스프링꺼
	private String uploadPath;

	@PostConstruct // 생성자 대신에 많이 쓴다, 뭔가 초기화 시켜야할때
	public void init(){
		// 프로젝트 실행하면 폴더가 없으니까 만들어주는 용도로 사용
		File tempFolder = new File(uploadPath);
		if (!tempFolder.exists()) {
			tempFolder.mkdir();
		}

		uploadPath = tempFolder.getAbsolutePath();
		log.info("-----------------------------");
		log.info(uploadPath);
	}

	public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {
		if (files == null || files.isEmpty()) {
			// return List.of();
			return null;
		}

		List<String> uploadNames = new ArrayList<>();
		for (MultipartFile file : files) {
			String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path savePath = Paths.get(uploadPath, savedName);
			try {
				Files.copy(file.getInputStream(), savePath); // 예외처리가 좀 필요함
				uploadNames.add(savedName);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return uploadNames;
	}
}
