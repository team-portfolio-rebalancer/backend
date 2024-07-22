package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.core.IsNull.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;

import io.restassured.response.ValidatableResponse;

public class CategoryAssetAcceptanceTest extends AcceptanceTest {

	@Autowired
	CategoryRepository categoryRepository;

	Long categoryId;

	@BeforeEach
	void saveCategory() {
		Category category = categoryRepository.save(new Category(1L, "주식", null));
		categoryId = category.getId();
	}

	@DisplayName("사용자가 카테고리 자산을 저장하고 200 OK를 반환한다.")
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

}
