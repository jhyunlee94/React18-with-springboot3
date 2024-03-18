package com.example.mallserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder // 나중에 상속을 쓸때 사용함
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
	// 얘는 상속이 맞아요 왜냐면 막 검색조건이 늘어나거나 막 그러니까요

	@Builder.Default
	private int page = 1;

	@Builder.Default
	private int size = 10;

}
