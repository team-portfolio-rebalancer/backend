package com.portfolio.rebalancer.dto.request;

import com.portfolio.rebalancer.domain.color.Color;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

	@NotBlank(message = RequestErrorCode.BLANK)
	@Pattern(regexp = Color.REGEX, message = RequestErrorCode.COLOR_REGEX)
	private String color;
}
