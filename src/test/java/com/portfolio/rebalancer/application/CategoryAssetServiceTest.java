package com.portfolio.rebalancer.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

@SpringBootTest
class CategoryAssetServiceTest {

	@Autowired
	private CategoryAssetService categoryAssetService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	DatabaseCleanUp databaseCleanUp;

	private Long categoryId;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
		Category category = categoryRepository.save(new Category(1L, "주식", "#FFFFFF"));
		categoryId = category.getId();
	}

	@DisplayName("카테고리 자산을 저장한다.")
	@Test
	void 카테고리_자산_생성() {
		// given
		AssetRequest assetRequest = new AssetRequest("360200", "ACE 미국S&P500", 16558L);

		CategoryAssetRequest request = new CategoryAssetRequest(
			categoryId,
			assetRequest,
			1L,
			100,
			"#FFFFFF"
		);

		// when
		Long saveId = categoryAssetService.save(request);

		// then
		assertNotNull(saveId);
	}
}
