package com.portfolio.rebalancer.dto.request;

import com.portfolio.rebalancer.domain.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryRequest {

    @NotNull(message = InvalidInputMessage.BLANK)
    private Long userId;

    @NotBlank(message = InvalidInputMessage.BLANK)
    private String name;

    @NotBlank(message = InvalidInputMessage.BLANK)
    @Pattern(regexp = Color.REGEX, message = InvalidInputMessage.COLOR_REGEX)
    private String color;
}
