package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.core.IsNull.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(properties = "spring.session.store-type=none", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryAssetAcceptanceTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@Autowired
	CategoryRepository categoryRepository;

	Long categoryId;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseCleanUp.execute();
		Category category = categoryRepository.save(new Category(1L, "주식", "#FFFFFF"));
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
			"#FFFFFF"
		);

		// when
		ValidatableResponse response = RestAssured.given().log().all()
			.body(categoryAssetRequest)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().post("/category-assets")
			.then().log().all();

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}

}
