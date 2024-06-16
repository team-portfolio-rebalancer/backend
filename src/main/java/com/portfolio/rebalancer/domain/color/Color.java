package com.portfolio.rebalancer.domain.color;

import java.util.regex.Pattern;

import com.portfolio.rebalancer.domain.exception.RebalancerException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Color {

	public static final String REGEX = "^#([a-fA-F0-9]{6})$";
	static final String REGEX_ERROR_MESSAGE = "색상 형식이 맞지 않습니다.";
	@Column(name = "color", nullable = false)
	private String value;

	public Color(final String value) {
		validateRegex(value);
		this.value = value;
	}

	private void validateRegex(final String value) {
		if (!Pattern.matches(REGEX, value)) {
			throw new RebalancerException(ColorErrorCode.INVALID_REGEX);
		}
	}
}
