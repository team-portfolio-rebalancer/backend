package com.portfolio.rebalancer.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
	}

	@DisplayName("카테고리를 저장한다.")
	@Test
	void 카테고리_생성() {
		// given
		CategoryRequest request = new CategoryRequest(1L, "주식", "#FFFFFF");

		// when
		Long saveId = categoryService.save(request.getUserId(), request);

		// then
		assertThat(saveId).isNotNull();
	}
}
