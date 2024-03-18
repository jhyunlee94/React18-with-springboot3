package com.example.mallserver.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageResponseDTO<E> {
	// 근데 이 response 는 대부분 페이지는 다 비슷해요
	// 목록데이터, 페이지에 관한 데이터 이렇게만 들어가는거죠

	// 만들어봐야하는데 이런 경우 여기서는 크게 3개가 필요해보임
	// dtoList 만들어서 줘야하고
	// totalCount 값이 있어야지만 페이징 처리 가능
	// 세번째는 pageRequestDTO 가 필요합니다.
	// 그래야지만 구성이 가능해 보임

	private List<E> dtoList;
    private List<Integer> pageNumList;

	// 검색조건, 현재페이지는 전부 pageRequestDTO 안에 있어요
	private PageRequestDTO pageRequestDTO;

	// 이전, 다음 , < >
	private boolean prev, next;

	private int totalCount, prevPage, nextPage, totalPage, current;

	public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {
		this.dtoList = dtoList;
		this.pageRequestDTO = pageRequestDTO;
		// 계산이 들어가야함
		this.totalCount = (int) total; // 다운 캐스팅

		// 끝페이지 end, 현재페이지 번호가 필요함
		int end = (int)(Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;

		// 시작값은 -9 한 값
		int start = end -9;

		// 진짜 마지막 페이지
		int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));

		end = end > last ? last : end;

		// 이전
		this.prev = start > 1;

		// 다음
		this.next = totalCount > end * pageRequestDTO.getSize();

		// 이때는 박싱같은거 쓰면 편하죠 -> int -> Integer 가 됨
		this.pageNumList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

		this.prevPage = prev ? start -1 : 0;

		this.nextPage = next ? end + 1 : 0;

	}

}
