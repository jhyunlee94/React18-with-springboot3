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
