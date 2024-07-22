package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.portfolio.rebalancer.domain.asset.Asset;
import com.portfolio.rebalancer.domain.asset.AssetRepository;
import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAsset;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAssetRepository;
import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;

import io.restassured.response.ValidatableResponse;

public class CategoryAssetAcceptanceTest extends AcceptanceTest {

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

	@DisplayName("카테고리 자산을 저장하고 200 OK를 반환한다.")
	@Test
	void create() {
		// given
		AssetRequest assetRequest = new AssetRequest("360200", "ACE 미국S&P500", 16558L);

		CategoryAssetRequest categoryAssetRequest = new CategoryAssetRequest(
			categoryId,
			assetRequest,
			1L,
			100,
			null
		);

		// when
		ValidatableResponse response = post("/category-assets", categoryAssetRequest);

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}

	@DisplayName("카테고리 자산 아이디로 조회하고 200 OK를 반환한다.")
	@Test
	void findById() {
		// given
		int id = categoryAssetId.intValue();

		// when
		ValidatableResponse response = get("/category-assets/" + id);

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("data.id", equalTo(id));
	}
}
