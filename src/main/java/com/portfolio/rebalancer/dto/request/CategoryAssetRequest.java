package com.portfolio.rebalancer.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
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

	private String color;
}
