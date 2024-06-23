package com.portfolio.rebalancer.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.rebalancer.domain.asset.Asset;
import com.portfolio.rebalancer.domain.asset.AssetRepository;
import com.portfolio.rebalancer.dto.request.AssetRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetService {

	private final AssetRepository assetRepository;

	@Transactional
	public Long save(final AssetRequest request) {
		Asset asset = new Asset(request.getCode(), request.getName(), request.getPrice());
		Asset savedAsset = assetRepository.save(asset);
		return savedAsset.getId();
	}
}
