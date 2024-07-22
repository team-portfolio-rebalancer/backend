package com.portfolio.rebalancer.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.portfolio.rebalancer.dto.request.CategoryRequest;

class CategoryServiceTest extends ServiceTest {

	@Autowired
	private CategoryService categoryService;

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
