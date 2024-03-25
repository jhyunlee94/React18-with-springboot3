package com.example.mallserver.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "memberRolesList")
public class Member {

	@Id
	private String email;

	private String pw;

	private String nickname;

	private boolean social;

	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<MemberRole> memberRolesList = new ArrayList<>();

	public void addRole(MemberRole memberRole) {
		memberRolesList.add(memberRole);
	}

	public void clearRole() {
		// ElementCollection 은 절대 바꿔치기하면 안됩니다.
		memberRolesList.clear();
	}

	public void changePw(String pw) {
		this.pw = pw;
	}

	public void changeNickname(String nickname) {
		this.nickname = nickname;
	}

	public void changeSocial(boolean social) {
		this.social = social;
	}
}
