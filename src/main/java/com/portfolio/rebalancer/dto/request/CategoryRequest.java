package com.portfolio.rebalancer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryRequest {

	@NotNull(message = RequestErrorCode.BLANK)
	private Long userId;

	@NotBlank(message = RequestErrorCode.BLANK)
	private String name;

	private String color;
}
