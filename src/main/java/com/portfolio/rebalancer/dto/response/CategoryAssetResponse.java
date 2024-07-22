package com.portfolio.rebalancer.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAsset;
import com.portfolio.rebalancer.domain.color.Color;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder
public class CategoryAssetResponse {

	private final Long id;
	private final CategoryResponse categoryResponse;
	private final AssetResponse assetResponse;
	private final Long amount;
	private final Integer aimPercentage;
	private final Color color;

	public static CategoryAssetResponse from(CategoryAsset categoryAsset) {
		return CategoryAssetResponse.builder()
			.id(categoryAsset.getId())
			.categoryResponse(CategoryResponse.from(categoryAsset.getCategory()))
			.assetResponse(AssetResponse.from(categoryAsset.getAsset()))
			.amount(categoryAsset.getAmount())
			.aimPercentage(categoryAsset.getAimPercentage())
			.color(categoryAsset.getColor())
			.build();
	}
}
