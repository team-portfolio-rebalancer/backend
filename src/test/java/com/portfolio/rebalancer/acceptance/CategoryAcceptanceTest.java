package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.portfolio.rebalancer.dto.request.CategoryRequest;

import io.restassured.response.ValidatableResponse;

public class CategoryAcceptanceTest extends AcceptanceTest {

	@DisplayName("사용자가 카테고리를 설정하고 200 OK를 반환한다.")
	@Test
	void create() {
		// given
		Long userId = 1L;
		String name = "주식";
		String color = "#FFFFF0";
		CategoryRequest categoryRequest = new CategoryRequest(userId, name, color);

		// when
		ValidatableResponse response = post("/categories", categoryRequest);

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}
}
