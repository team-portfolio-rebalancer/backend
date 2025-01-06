package com.portfolio.rebalancer.presentation;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.rebalancer.application.AssetService;
import com.portfolio.rebalancer.dto.request.AssetRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class AssetController implements AssetControllerDocs {

	private final AssetService assetService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid final AssetRequest request) {
		Long id = assetService.save(request);
		return ResponseEntity.created(URI.create("/assets/" + id)).build();
	}
}
