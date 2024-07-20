package com.portfolio.rebalancer.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RebalancerException extends RuntimeException {

	private final int statusCode;
	private final String errorCode;
	private final String message;

	public RebalancerException(ErrorCode code) {
		statusCode = code.getStatusCode();
		errorCode = code.getErrorCode();
		message = code.getMessage();
	}
}
