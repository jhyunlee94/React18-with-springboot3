package com.example.mallserver.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.mallserver.domain.Product;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testInsert() {

		Product product = Product.builder()
			.pname("Test")
			.pdesc("Test Desc")
			.price(1000)
			.build();

		product.addImageString(UUID.randomUUID()+"_"+"IMAGE1.jpg");
		product.addImageString(UUID.randomUUID()+"_"+"IMAGE2.jpg");
		productRepository.save(product);
	}

	// @Transactional
	@Test
	public void testRead() {
		Long pno = 1L;
		Optional<Product> result = productRepository.findById(pno);
		Product product = result.orElseThrow();
		log.info(product);
		log.info(product.getImageList());
	}

	@Test
	public void testRead2() {
		Long pno = 1L;
		Optional<Product> result = productRepository.selectOne(pno);
		Product product = result.orElseThrow();
		log.info(product);
		log.info(product.getImageList());
	}

	@Commit
	@Transactional
	@Test
	public void testDelete() {
		Long pno = 2L;
		productRepository.updateToDelete(pno, true);
	}

	@Test
	@Commit
	@Transactional
	public void testUpdate() {
		Product product = productRepository.selectOne(1L).get();

		product.changePrice(3000);

		product.clearList();
		product.addImageString(UUID.randomUUID()+"_"+"PIMAGE1.jpg");
		product.addImageString(UUID.randomUUID()+"_"+"PIMAGE2.jpg");
		product.addImageString(UUID.randomUUID()+"_"+"PIMAGE2.jpg");
		productRepository.save(product);
	}

}
