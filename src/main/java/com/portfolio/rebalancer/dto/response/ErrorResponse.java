package com.portfolio.rebalancer.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

	private final String code;
	private final String message;
}
