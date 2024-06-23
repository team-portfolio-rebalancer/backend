package com.portfolio.rebalancer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RebalancerResponse<T> {

	private final boolean success;
	private final ErrorResponse error;
	private final T data;

	public static <T> RebalancerResponse<T> success(T data) {
		return new RebalancerResponse<>(true, null, data);
	}

	public static <T> RebalancerResponse<T> fail(String errorCode, String message) {
		ErrorResponse error = new ErrorResponse(errorCode, message);
		return new RebalancerResponse<>(false, error, null);
	}
}
