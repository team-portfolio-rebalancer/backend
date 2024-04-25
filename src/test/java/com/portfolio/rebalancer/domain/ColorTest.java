package com.portfolio.rebalancer.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ColorTest {

    @DisplayName("잘못된 형식의 color가 들어오면 예외를 발생한다,")
    @ParameterizedTest
    @ValueSource(strings = {"FFFFFF", "#FFFFF", "#FFFFFFF", "#FFFFFG"})
    void throws_exception_when_regex_is_wrong(String color) {
        // given & when & then
        assertThatThrownBy(() -> new Color(color)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Color.REGEX_ERROR_MESSEAGE);
    }
}
