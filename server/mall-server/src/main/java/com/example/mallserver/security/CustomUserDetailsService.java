package com.example.mallserver.security;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mallserver.domain.Member;
import com.example.mallserver.dto.MemberDTO;
import com.example.mallserver.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username 이 우리한테는 Email 이죠
		// UserDetails 는 우리한테는 MemberDTO 가 되는거예요
		// 이 상속관계가 이 타입을 잘 봐야해요 UserDetails api docs

		log.info("-----------loadUserByUsername-----------" + username);

		Member member = memberRepository.getWithRoles(username);

		if (member == null) {
			throw new UsernameNotFoundException("Not Found");
		}

		MemberDTO memberDTO = new MemberDTO(
			member.getEmail(),
			member.getPw(),
			member.getNickname(),
			member.isSocial(),
			member.getMemberRolesList()
				.stream()
				.map(memberRole -> memberRole.name())
				.collect(Collectors.toList())
		);

		log.info(memberDTO);

		return memberDTO;
	}
}
