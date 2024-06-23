package com.portfolio.rebalancer.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.portfolio.rebalancer.domain.category.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder
public class CategoryResponse {

	private final Long id;
	private final String name;
	private final String color;

	public static CategoryResponse from(final Category category) {
		return CategoryResponse.builder()
			.id(category.getId())
			.name(category.getName())
			.color(category.getColor().getValue())
			.build();
	}
}
