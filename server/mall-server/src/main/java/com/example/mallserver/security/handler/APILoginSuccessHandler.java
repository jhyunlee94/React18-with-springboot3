package com.example.mallserver.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.mallserver.dto.MemberDTO;
import com.example.mallserver.util.JWTUtil;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		log.info("---------------------");
		log.info(authentication);
		log.info("---------------------");

		MemberDTO memberDTO = (MemberDTO)authentication.getPrincipal();

		Map<String, Object> claims = memberDTO.getClaims();

		String accessToken = JWTUtil.generateToken(claims, 10); // 10분임
		String refreshToken = JWTUtil.generateToken(claims, 60 * 24);

		claims.put("accessToken", accessToken);
		claims.put("refreshToken", refreshToken);

		Gson gson = new Gson();

		String jsonStr = gson.toJson(claims);

		response.setContentType("application/json; charset=UTF-8");

		// 만약 실제로 이렇게하면 안되는게
		// 패스워드나 그런게 다 보이는 상황 고려해주세요.
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);
		printWriter.close();

	}
}
