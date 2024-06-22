package com.portfolio.rebalancer.domain.category;

import com.portfolio.rebalancer.domain.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CategoryErrorCode implements ErrorCode {

	CATEGORY_NOT_FOUND(400, "CATEGORY_001", "해당 id의 카테고리가 없습니다."),
	;

	private final int statusCode;
	private final String errorCode;
	private final String message;
}
