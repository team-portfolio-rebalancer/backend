package com.portfolio.rebalancer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Color {

    static final String REGEX_ERROR_MESSEAGE = "색상 형식이 맞지 않습니다.";
    public static final String REGEX = "^#([a-fA-F0-9]{6})$";

    @Column(name = "color", nullable = false)
    private String value;

    public Color(final String value) {
        validateRegex(value);
        this.value = value;
    }

    private void validateRegex(final String value) {
        if (!Pattern.matches(REGEX, value)) {
            throw new IllegalArgumentException(REGEX_ERROR_MESSEAGE);
        }
    }
}
