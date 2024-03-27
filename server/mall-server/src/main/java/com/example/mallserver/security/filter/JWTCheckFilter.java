package com.example.mallserver.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.mallserver.dto.MemberDTO;
import com.example.mallserver.util.JWTUtil;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

	// OncePerRequestFilter 는 모든 리퀘스트에 대해 하는겁니다.

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// 어떤 경로로 들어오면 필터링을 해야하고
		// 어떤 경로로 오면 검사를 안해도된다.
		// 대표적으로 사용자가 api/member/login에 들어올때는 필터를 태우면 안되는거죠
		// 그런 경로를 빼기위해서 쓰는겁니다.

		// true == not checking
		String path = request.getRequestURI();

		log.info("check uri---------------" + path);

		if (path.startsWith("/api/member")) {
			// if (path.startsWith("/api")) {
			return true;
		}

		// false == check 한다
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		log.info("--------------------");

		log.info("--------------------");

		log.info("--------------------");

		String authHeaderStr = request.getHeader("Authorization");

		// Bearer // 총 7개 이 뒤에 JWT 문자열 나옴

		// String accessToken = authHeaderStr.substring(7);
		// Map<String, Object> claims = JWTUtil.validateToken(accessToken);
		//
		// log.info(claims);

		try {
			String accessToken = authHeaderStr.substring(7);
			Map<String, Object> claims = JWTUtil.validateToken(accessToken);

			log.info("JWT claims: " + claims);

			// dest 다음 목적지로 가게하는 기능
			// filterChain.doFilter(request, response); // before

			// after
			// 사용자의 정보 가져올 수 있음
			String email = (String)claims.get("email");
			String pw = (String)claims.get("pw");
			String nickname = (String)claims.get("nickname");
			Boolean social = (Boolean)claims.get("social");
			List<String> roleNames = (List<String>)claims.get("roleNames");

			// 이 내용을 토대로 DTO 생성 가능
			MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);

			log.info("-------------------------------");
			log.info(memberDTO);
			log.info(memberDTO.getAuthorities());

			// Spring Security Context 가 사용하는 토큰을 만들어 줌
			// 단점은 매번 호출 될때마다 넣어주고 그거를 이용해서 preAuthorize 한다는 단점이 있어요
			// 반드시 필요한건 아닌데 혹시라도 이런거 있으면 어떤가라고 한거예요
			// 지금 사실 이렇게하면은 지금 이렇게된다 할려고 DTO만 보여주는건데 만약 DB를 거치게되면 api 호출할때마다
			// DB를 호출하게됩니다. 그래서 이럴때 Redis 를 사용하면 좋아요
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO,
				pw, memberDTO.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			log.error("JWT Check Error...............");
			log.error(e.getMessage());

			// 에러메세지를 간단하게 한건데
			// 이걸 복잡하게도 가능합니다.
			Gson gson = new Gson();
			String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

			response.setContentType("application/json");
			PrintWriter printWriter = response.getWriter();
			printWriter.println(msg);
			printWriter.close();
		}

	}

}
