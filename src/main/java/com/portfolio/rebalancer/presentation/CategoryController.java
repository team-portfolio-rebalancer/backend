package com.portfolio.rebalancer.presentation;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rebalancer.application.CategoryService;
import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.dto.response.CategoryResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerDocs {

	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid final CategoryRequest request) {
		Long id = categoryService.save(request.getUserId(), request);
		return ResponseEntity.created(URI.create("/categories/" + id)).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
		CategoryResponse response = categoryService.findById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponse>> findAll() {
		List<CategoryResponse> response = categoryService.findAll();
		return ResponseEntity.ok(response);
	}

}
