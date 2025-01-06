package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.dto.request.CategoryUpdatingRequest;

import io.restassured.response.ValidatableResponse;

public class CategoryAcceptanceTest extends AcceptanceTest {

	@Autowired
	private CategoryRepository categoryRepository;

	private Long categoryId;

	@BeforeEach
	void saveCategory() {
		Category category = categoryRepository.save(new Category(1L, "주식", "#FFFFFF"));
		categoryId = category.getId();
	}

	@DisplayName("카테고리를 저장하고 200 OK를 반환한다.")
	@Test
	void create() {
		// given
		CategoryRequest categoryRequest = new CategoryRequest(1L, "주식", null);

		// when
		ValidatableResponse response = post("/categories", categoryRequest);

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}

	@DisplayName("카테고리 아이디로 조회하고 200 OK를 반환한다.")
	@Test
	void findById() {
		// given
		int id = categoryId.intValue();

		// when
		ValidatableResponse response = get("/categories/" + id);

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("data.id", equalTo(id));
	}

	@DisplayName("모든 카테고리를 조회하고 200 OK를 반환한다.")
	@Test
	void findAll() {
		// given
		categoryRepository.save(new Category(1L, "채권", null));
		categoryRepository.save(new Category(1L, "현금", null));

		// when
		ValidatableResponse response = get("/categories");

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("data.size()", equalTo(3));
	}

	@DisplayName("카테고리를 수정하고 200 OK를 반환한다.")
	@Test
	void updateById() {
		// given
		CategoryUpdatingRequest categoryUpdatingRequest = new CategoryUpdatingRequest("주식", null);

		// when
		ValidatableResponse response = patch("/categories/" + categoryId, categoryUpdatingRequest);

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("data.name", equalTo(categoryUpdatingRequest.getName()))
			.body("data.color", equalTo(categoryUpdatingRequest.getColor()));
	}
}
