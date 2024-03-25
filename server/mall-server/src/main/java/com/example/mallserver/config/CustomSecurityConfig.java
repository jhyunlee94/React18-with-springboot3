package com.example.mallserver.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {
	// spring 3.1 넘어가면서 굉장히 많이 바뀝니다.

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("-----------------security config------------------");

		// CSRF(Request 위조 방지) 는 달라져요
		http.cors(httpSecurityCorsConfigurer -> {
			httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
		});

		http.csrf(AbstractHttpConfigurer::disable);
		// http.csrf(httpSecurityCsrfConfigurer -> {
		// 	httpSecurityCsrfConfigurer.disable();
		// });

		return http.build();
	}

	// 패스워드 인코더(필수)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		// @Bean 을 만들때는 보통 메소드 이름과 동일하게 가죠?
		CorsConfiguration configuration = new CorsConfiguration();

		// CORS 설정

		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
