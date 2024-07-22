package com.portfolio.rebalancer.presentation;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rebalancer.application.CategoryAssetService;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;
import com.portfolio.rebalancer.dto.response.CategoryAssetResponse;
import com.portfolio.rebalancer.dto.response.RebalancerResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category-assets")
@RequiredArgsConstructor
public class CategoryAssetController implements CategoryAssetControllerDocs {

	private final CategoryAssetService categoryAssetService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid final CategoryAssetRequest request) {
		Long id = categoryAssetService.save(request);
		return ResponseEntity.created(URI.create("/category-assets/" + id)).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<RebalancerResponse<CategoryAssetResponse>> findById(@PathVariable Long id) {
		CategoryAssetResponse response = categoryAssetService.findById(id);
		return ResponseEntity.ok(RebalancerResponse.success(response));
	}
}
