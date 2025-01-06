package com.portfolio.rebalancer.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.color.Color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryUpdatingRequest {

	private String name;
	private String color;

	public Category toDomain() {
		return Category.builder()
			.name(name)
			.color(new Color(color))
			.build();
	}
}
