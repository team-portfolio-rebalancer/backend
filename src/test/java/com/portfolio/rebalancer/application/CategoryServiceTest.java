package com.portfolio.rebalancer.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.domain.exception.RebalancerException;
import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.dto.response.CategoryResponse;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	private Long categoryId;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
		Category category = categoryRepository.save(new Category(1L, "주식", "#FFFFFF"));
		categoryId = category.getId();
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

	@DisplayName("카테고리 아이디로 조회한다.")
	@Test
	void 카테고리_조회_성공() {
		// given & when
		CategoryResponse categoryResponse = categoryService.findById(categoryId);

		// then
		assertThat(categoryResponse.getId()).isEqualTo(categoryId);
	}

	@DisplayName("없는 카테고리 아이디로 조회하면 예외가 발생한다.")
	@Test
	void 카테고리_조회_실패() {
		// given & when
		Long notExistCategoryId = categoryId + 1L;

		// then
		assertThatThrownBy(() -> categoryService.findById(notExistCategoryId))
			.isInstanceOf(RebalancerException.class);
	}

	@DisplayName("모든 카테고리를 조회한다.")
	@Test
	void 카테고리_전체_조회() {
		// given & when
		categoryRepository.save(new Category(1L, "채권", "#FFFFFF"));
		categoryRepository.save(new Category(1L, "현금", "#FFFFFF"));

		// then
		assertThat(categoryService.findAll()).hasSize(3);
	}
}
