package com.portfolio.rebalancer.domain.categoryasset;

import com.portfolio.rebalancer.domain.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CategoryAssetErrorCode implements ErrorCode {

	CATEGORY_ASSET_NOT_FOUND(400, "CATEGORY_ASSET_001", "해당 id의 카테고리 자산이 없습니다."),
	;

	private final int statusCode;
	private final String errorCode;
	private final String message;
}
