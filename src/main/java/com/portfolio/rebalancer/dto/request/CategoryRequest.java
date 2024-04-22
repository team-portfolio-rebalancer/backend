package com.portfolio.rebalancer.dto.request;

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
    @Pattern(regexp = "^#?([a-fA-F0-9]{6})$")
    private String color;
}
