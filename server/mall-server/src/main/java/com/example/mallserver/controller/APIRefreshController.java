package com.example.mallserver.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mallserver.util.CustomJWTException;
import com.example.mallserver.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {
	// 필터도 가능하지만 귀찮으니까,,

	@RequestMapping("/api/member/refresh")
	public Map<String, Object> refresh(
		@RequestHeader("Authorization") String authHeader,
		String refreshToken) {

		if (refreshToken == null) {
			throw new CustomJWTException("NULL_REFRESH");
		}

		if (authHeader == null || authHeader.length() < 7) {
			throw new CustomJWTException("INVALID_STRING");
		}

		// Bearer xxxx
		String accessToken = authHeader.substring(7);

		// AccessToken의 만료여부 확인
		// 메소드 만드는게 좋아서 하나 만들게요
		if (checkExpiredToken(accessToken) == false) {
			// 만료가 되지 않은거니까 그냥 그대로 원래대로 보내줌
			return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
		}

		// Refresh토큰 검증
		Map<String, Object> claims = JWTUtil.validateToken(refreshToken);

		log.info("refresh ... claims: " + claims);
		String newAccessToken = JWTUtil.generateToken(claims, 10);
		String newRefreshToken = checkTime(
			(Integer)claims.get("exp")) == true ? JWTUtil.generateToken(claims, 60 * 24) : refreshToken;
		return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
	}

	private boolean checkTime(Integer exp) {
		// JWT exp를 날짜로 변환
		Date expDate = new Date((long)exp * (1000));

		// 현재 시간과의 차이 계산 - 밀리세컨즈
		long gap = expDate.getTime() - System.currentTimeMillis();

		// 분단위 계산
		long leftMin = gap / (1000 * 60);

		// 1시간도 안남았는지..
		return leftMin < 60;
	}

	private boolean checkExpiredToken(String token) {
		try {
			JWTUtil.validateToken(token);
		} catch (CustomJWTException ex) {
			if (ex.getMessage().equals("Expired")) {
				return true;
			}
		}
		return false;
	}
}
