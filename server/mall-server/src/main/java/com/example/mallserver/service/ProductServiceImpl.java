package com.example.mallserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.mallserver.domain.Product;
import com.example.mallserver.domain.ProductImage;
import com.example.mallserver.dto.PageRequestDTO;
import com.example.mallserver.dto.PageResponseDTO;
import com.example.mallserver.dto.ProductDTO;
import com.example.mallserver.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	@Override
	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("pno").descending());

		Page<Object[]> result = productRepository.selectList(pageable);

		// Object[] => 0번째 product , 1번째 productImage입니다

		// a -> b로 바꾸는거면 forEach 보단 Map 쓰죠, 하지만 .get() 은 stream 으로 나와서 map 이 쉬움
		List<ProductDTO> dtoList = result.get().map((arr) -> {
			ProductDTO productDTO = null;

			// 한번에 쿼리 날려서 프로젝션을 쓰는 방법도 있어요, 여기선 안씀, 공부해보세요
			Product product = (Product) arr[0];
			ProductImage productImage = (ProductImage) arr[1];

			productDTO = ProductDTO.builder()
				.pno(product.getPno())
				.pname(product.getPname())
				.pdesc(product.getPdesc())
				.price(product.getPrice())
				.build();

			String imageStr = productImage.getFileName();
			productDTO.setUploadFileNames(List.of(imageStr));


			return productDTO;
		}).collect(Collectors.toList());

		long totalCount = result.getTotalElements();

		return PageResponseDTO.<ProductDTO>withAll()
			.dtoList(dtoList)
			.totalCount(totalCount)
			.pageRequestDTO(pageRequestDTO)
			.build();
	}

	@Override
	public Long register(ProductDTO productDTO) {

		Product product = dtoToEntity(productDTO);

		log.info("------------------------------");
		log.info(product);
		log.info(product.getImageList());
		log.info("------------------------------");

		Long pno = productRepository.save(product).getPno();
		return pno;
	}

	private Product dtoToEntity(ProductDTO productDTO) {
		Product product = Product.builder()
			.pno(productDTO.getPno())
			.pdesc(productDTO.getPdesc())
			.pname(productDTO.getPname())
			.price(productDTO.getPrice())
			.build();

		List<String> uploadFileNames = productDTO.getUploadFileNames(); // 업로드된 파일 이름이 있을거고, 문자열임
		// 엔티티안에 컬렉션은 새로 만들면 절대 안됩니다.

		if(uploadFileNames == null || uploadFileNames.size() == 0) {
			return product;
		}

		uploadFileNames.forEach(fileName -> {
			product.addImageString(fileName);
		});

		return product;
	}
}


