package com.example.mallserver.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Table(name = "tbl_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 연관을 맺던 엘리먼트 컬렉션을 맺든 tostring에서 빼고 생각하세요
@ToString(exclude = "imageList")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;

	private String pname;

	private int price;
	private String pdesc;
	private boolean delFlag;

	@ElementCollection
	// 엘리먼트 컬렉션 자체가 주인공이 안됨, 상품정보를 수정한다, 관리가 많아지기에 manytoone 으로 빼는 경우가 많음
	/**
	 * 네. 맞습니다. 데이터베이스에는 시간 관계를 표현하지 않기 때문에 @ManyToOne등을 이용해서 표현할 수도 있습니다.
	 * 이런 경우에는 개인적으로 주체와 시간을 기준으로 생각합니다.
	 * 예를 들어 '게시물'과 '댓글'의 경우 만드는 사람이 동일하지 않고 사용되는 시간도 전혀 다르게 됩니다. 이런 경우라면 별도의 도메인으로 분리해서 봅니다.
	 * 반대로 '주문'과 '주문 상세'를 생각해 보면 결국 '주문 상세'를 수정하는 것이 '주문'을 수정하는 것과 동의어가 됩니다. 이런 경우라면 @ElementCollection을 이용하는게 낫다고 생각합니다.
	 * */
	@Builder.Default
	private List<ProductImage> imageList = new ArrayList<>();

	public void changePname(String pname) {
		this.pname = pname;
	}

	public void changePrice(int price) {
		this.price = price;
	}

	public void changePdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public void changeDel(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public void addImage(ProductImage image) {
		image.setOrd(imageList.size());
		imageList.add(image);
	}

	public void addImageString(String fileName) {
		ProductImage productImage = ProductImage.builder()
			.fileName(fileName)
			.build();

		addImage(productImage);
	}

	public void clearList() {
		this.imageList.clear();
	}
}
