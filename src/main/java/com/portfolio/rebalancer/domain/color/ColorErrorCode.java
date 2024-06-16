package com.portfolio.rebalancer.domain.color;

import com.portfolio.rebalancer.domain.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ColorErrorCode implements ErrorCode {
	INVALID_REGEX(400, "COLOR_001", "색상의 형식이 맞지 않습니다."),
	;

	private final int statusCode;
	private final String errorCode;
	private final String message;
}
