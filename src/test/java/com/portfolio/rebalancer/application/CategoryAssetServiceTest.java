package com.portfolio.rebalancer.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.portfolio.rebalancer.domain.asset.Asset;
import com.portfolio.rebalancer.domain.asset.AssetRepository;
import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAsset;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAssetRepository;
import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;

class CategoryAssetServiceTest extends ServiceTest {

	@Autowired
	private CategoryAssetService categoryAssetService;

	@Autowired
	private CategoryAssetRepository categoryAssetRepository;

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private Long categoryAssetId;

	private Long categoryId;

	@BeforeEach
	void saveCategoryAsset() {
		Category category = categoryRepository.save(new Category(1L, "주식", null));
		categoryId = category.getId();

		Asset asset = assetRepository.save(new Asset("360200", "ACE 미국S&P500", 16558L));

		CategoryAsset categoryAsset = categoryAssetRepository.save(new CategoryAsset(
			category,
			asset,
			10L,
			100,
			null
		));
		categoryAssetId = categoryAsset.getId();
	}

	@DisplayName("카테고리 자산을 저장한다.")
	@Test
	void 카테고리_자산_생성() {
		// given
		CategoryAssetRequest request = new CategoryAssetRequest(
			categoryId,
			new AssetRequest("360200", "ACE 미국S&P500", 16558L),
			1L,
			100,
			null
		);

		// when
		Long saveId = categoryAssetService.save(request);

		// then
		assertNotNull(saveId);
	}
}
