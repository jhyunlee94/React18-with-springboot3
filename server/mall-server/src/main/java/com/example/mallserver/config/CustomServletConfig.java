package com.example.mallserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.mallserver.formatter.LocalDateFormatter;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {
	// 이거 해주는 이유는 오버라이딩 할 메서드가 많아집니다.
	// CORS 설정도 여기에 들어감

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// LocalDateFormatter 는 어노테이션이 없기에 여기에 추가해주는거예요

		log.info("----------------------------------");
		log.info("addFormatters"); // 동작을 위한 log

		registry.addFormatter(new LocalDateFormatter());
	}

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// 	registry.addMapping("/**") // 모든 곳
	// 		.maxAge(500)
	// 		.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // OPTIONS = 미리한번 찔러볼때
	// 		.allowedOrigins("*");
	// }
}
