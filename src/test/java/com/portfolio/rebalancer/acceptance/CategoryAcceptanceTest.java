package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.Matchers.*;

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
import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(properties = "spring.session.store-type=none", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryAcceptanceTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@Autowired
	private CategoryRepository categoryRepository;

	private Long categoryId;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseCleanUp.execute();
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
		CategoryRequest categoryRequest = new CategoryRequest(1L, name, color);

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

	private ValidatableResponse post(final String uri, final Object requestBody) {
		return RestAssured.given().log().all()
			.body(requestBody)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().post(uri)
			.then().log().all();
	}

	private ValidatableResponse get(final String uri) {
		return RestAssured.given().log().all()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().get(uri)
			.then().log().all();
	}
}
