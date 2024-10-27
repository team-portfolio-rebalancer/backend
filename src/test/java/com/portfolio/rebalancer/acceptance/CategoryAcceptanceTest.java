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

	@DisplayName("카테고리 아이디로 조회하고 200 OK를 반환한다.")
	@Test
	void findById() {
		// given
		int id = categoryId.intValue();

		// when
		ValidatableResponse response = get("/categories/" + id);

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("id", equalTo(id));
	}

	@DisplayName("모든 카테고리를 조회하고 200 OK를 반환한다.")
	@Test
	void findAll() {
		// given
		categoryRepository.save(new Category(1L, "채권", "#FFFFFF"));
		categoryRepository.save(new Category(1L, "현금", "#FFFFFF"));

		// when
		ValidatableResponse response = get("/categories");

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("size()", equalTo(3));
	}
}
