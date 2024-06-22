package com.portfolio.rebalancer.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.dto.request.CategoryRequest;

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
}
