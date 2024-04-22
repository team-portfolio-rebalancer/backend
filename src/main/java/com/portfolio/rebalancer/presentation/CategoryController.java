package com.portfolio.rebalancer.presentation;

import com.portfolio.rebalancer.application.CategoryService;
import com.portfolio.rebalancer.dto.request.CategoryRequest;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid final CategoryRequest request) {
        Long id = categoryService.save(request.getUserId(), request);
        return ResponseEntity.created(URI.create("/categories/" + id)).build();
    }
}
