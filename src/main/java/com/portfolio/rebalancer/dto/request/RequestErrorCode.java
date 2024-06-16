package com.portfolio.rebalancer.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RequestErrorCode {

	private static final String DELIMITER = ",";

	static final String BLANK = "INPUT_001" + DELIMITER + "입력값이 없습니다.";
	static final String COLOR_REGEX = "INPUT_002" + DELIMITER + "색상 형식이 맞지 않습니다.";

	public static String[] splitCodeAndMessage(final String error) {
		System.out.println(error);
		return error.split(DELIMITER);
	}
}
