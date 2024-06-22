package com.portfolio.rebalancer.dto.request;

import com.portfolio.rebalancer.domain.color.Color;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryAssetRequest {

	private Long categoryId;

	private AssetRequest assetRequest;

	@NotNull(message = RequestErrorCode.BLANK)
	@Min(value = 1L, message = RequestErrorCode.AMOUNT_MIN)
	private Long amount;

	@NotNull(message = RequestErrorCode.BLANK)
	@Min(value = 0, message = RequestErrorCode.AIM_PERCENTAGE_RANGE)
	@Max(value = 100, message = RequestErrorCode.AIM_PERCENTAGE_RANGE)
	private Integer aimPercentage;

	@NotBlank(message = RequestErrorCode.BLANK)
	@Pattern(regexp = Color.REGEX, message = RequestErrorCode.COLOR_REGEX)
	private String color;
}
