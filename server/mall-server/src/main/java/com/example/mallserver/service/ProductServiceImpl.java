package com.example.mallserver.service;

import java.util.List;
import java.util.Optional;
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
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
			Sort.by("pno").descending());

		Page<Object[]> result = productRepository.selectList(pageable);

		// Object[] => 0번째 product , 1번째 productImage입니다

		// a -> b로 바꾸는거면 forEach 보단 Map 쓰죠, 하지만 .get() 은 stream 으로 나와서 map 이 쉬움
		List<ProductDTO> dtoList = result.get().map((arr) -> {
			ProductDTO productDTO = null;

			// 한번에 쿼리 날려서 프로젝션을 쓰는 방법도 있어요, 여기선 안씀, 공부해보세요
			Product product = (Product)arr[0];
			ProductImage productImage = (ProductImage)arr[1];

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

	@Override
	public ProductDTO get(Long pno) {
		Optional<Product> result = productRepository.findById(pno);
		Product product = result.orElseThrow();
		ProductDTO productDTO = entityToDTO(product);

		return productDTO;
	}

	@Override
	public void modify(ProductDTO productDTO) {
		// 조회
		Optional<Product> result = productRepository.findById(productDTO.getPno());

		// 변경 내용 반영
		Product product = result.orElseThrow();
		product.changePrice(productDTO.getPrice());
		product.changePname(productDTO.getPname());
		product.changePdesc(productDTO.getPdesc());
		product.changeDel(productDTO.isDelFlag());

		// 이미지 처리 - 목록을 비워야함
		// 업로드 되면 문자열로 저장됨
		List<String> uploadFileNames = productDTO.getUploadFileNames();

		product.clearList();

		if (uploadFileNames != null && !uploadFileNames.isEmpty()) {
			uploadFileNames.forEach(uploadName -> {
				product.addImageString(uploadName);
			});
		}
		// 저장, 이때 파일이 문제임, 변경이 된건지 아닌지 알 수가 없음
		productRepository.save(product);
	}

	@Override
	public void remove(Long pno) {
		// 원래는 flag 값 변경해주는거임 삭제하면 안됨 아래는 그냥 해보는 예시임
		productRepository.deleteById(pno);
	}

	private ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = ProductDTO.builder()
			.pno(product.getPno())
			.pname(product.getPname())
			.pdesc(product.getPdesc())
			.price(product.getPrice())
			.delFlag(product.isDelFlag())
			.build();

		List<ProductImage> imageList = product.getImageList();

		if (imageList == null | imageList.size() == 0) {
			return productDTO;
		}

		List<String> fileNameList = imageList.stream().map(productImage ->
			productImage.getFileName()).toList();
		productDTO.setUploadFileNames(fileNameList);
		return productDTO;
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

		if (uploadFileNames == null || uploadFileNames.size() == 0) {
			return product;
		}

		uploadFileNames.forEach(fileName -> {
			product.addImageString(fileName);
		});

		return product;
	}
}


