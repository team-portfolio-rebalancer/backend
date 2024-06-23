package com.portfolio.rebalancer.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryErrorCode;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.domain.exception.RebalancerException;
import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.dto.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public Long save(final Long userId, final CategoryRequest request) {
		Category category = new Category(userId, request.getName(), request.getColor());
		Category savedCategory = categoryRepository.save(category);
		return savedCategory.getId();
	}

	public CategoryResponse findById(final Long id) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new RebalancerException(CategoryErrorCode.CATEGORY_NOT_FOUND));
		return CategoryResponse.from(category);
	}

	public List<CategoryResponse> findAll() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream()
			.map(CategoryResponse::from)
			.collect(Collectors.toList());
	}
}
