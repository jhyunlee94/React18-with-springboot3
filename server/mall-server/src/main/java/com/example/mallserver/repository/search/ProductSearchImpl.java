package com.example.mallserver.repository.search;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.mallserver.domain.Product;
import com.example.mallserver.domain.QProduct;
import com.example.mallserver.domain.QProductImage;
import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.ProductDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

	public ProductSearchImpl() {
		super(Product.class);
	}

	@Override
	public PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO) {
		log.info("-----------------searchList------------------");
		// 조회, 조인할때 좀 다릅니다.
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
			Sort.by("pno").descending());

		QProduct product = QProduct.product;
		QProductImage productImage = QProductImage.productImage;

		// JPQL 쿼리
		JPQLQuery<Product> query = from(product);
		// 엔티티가 아니기 때문에 조심해야 함 엘리먼트 잖아요?
		query.leftJoin(product.imageList, productImage);
		query.where(productImage.ord.eq(0));

		Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query);

		// select 라는 걸 이용해서 뽑을수도있어요 무슨소리냐면은
		List<Tuple> productList = query.select(product, productImage).fetch();

		// 쿼리 실행될려면 fetch() 를해줘야함
		// List<Product> productList = query.fetch();
		long count = query.fetchCount();

		log.info("====================================");
		log.info(productList);

		// DTO 로 변환해줘야함 이거는 안했지만 쓸거면 해야해요

		return null;
	}
}
