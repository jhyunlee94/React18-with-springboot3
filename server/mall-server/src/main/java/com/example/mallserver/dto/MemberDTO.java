package com.example.mallserver.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberDTO extends User {

	private String email;
	private String pw;
	private String nickname;
	private boolean social;
	private List<String> roleNames = new ArrayList<>();

	public MemberDTO(String email, String pw, String nickname, boolean social, List<String> roleNames) {
		super(
			email,
			pw,
			// 스프링 시큐리티가 쓰는건 권한으로 만들어줘야해요
			// 이거는 스프링 시큐리티의 기본적인 예제를 알아야합니다.
			// 우리는 문자열로 가지면 되는데 시큐리티는 객체로 가져야해요
			roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList())
		);

		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.social = social;
		this.roleNames = roleNames;
	}

	public Map<String, Object> getClaims() {
		Map<String, Object> dataMap = new HashMap<>();

		dataMap.put("email", email);
		dataMap.put("pw", pw);
		dataMap.put("nickname", nickname);
		dataMap.put("social", social);
		dataMap.put("roleNames", roleNames);
		return dataMap;
	}
}
