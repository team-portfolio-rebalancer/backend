package com.portfolio.rebalancer.dto.request;

import com.portfolio.rebalancer.domain.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryRequest {

    private Long userId;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = Color.REGEX, message = "색상 형식이 맞지 않습니다.")
    private String color;
}
