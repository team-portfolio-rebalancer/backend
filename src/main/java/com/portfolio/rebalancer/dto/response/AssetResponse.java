package com.portfolio.rebalancer.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.portfolio.rebalancer.domain.asset.Asset;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder
public class AssetResponse {

	private final Long id;
	private final String code;
	private final String name;
	private final Long price;

	public static AssetResponse from(final Asset asset) {
		return AssetResponse.builder()
			.id(asset.getId())
			.code(asset.getCode())
			.name(asset.getName())
			.price(asset.getPrice())
			.build();
	}
}
