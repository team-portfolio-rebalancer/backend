package com.portfolio.rebalancer.application;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.rebalancer.domain.asset.Asset;
import com.portfolio.rebalancer.domain.asset.AssetRepository;
import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.category.CategoryErrorCode;
import com.portfolio.rebalancer.domain.category.CategoryRepository;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAsset;
import com.portfolio.rebalancer.domain.categoryasset.CategoryAssetRepository;
import com.portfolio.rebalancer.domain.exception.RebalancerException;
import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryAssetService {

	private final CategoryRepository categoryRepository;
	private final AssetRepository assetRepository;
	private final CategoryAssetRepository categoryAssetRepository;

	@Transactional
	public Long save(final CategoryAssetRequest request) {

		Long categoryId = request.getCategoryId();
		Category foundCategory = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new RebalancerException(CategoryErrorCode.CATEGORY_NOT_FOUND));

		AssetRequest assetRequest = request.getAssetRequest();
		Asset foundAsset = findOrSaveAsset(assetRequest);

		CategoryAsset categoryAsset = new CategoryAsset(
			foundCategory,
			foundAsset,
			request.getAmount(),
			request.getAimPercentage(),
			request.getColor()
		);

		CategoryAsset savedCategoryAsset = categoryAssetRepository.save(categoryAsset);
		return savedCategoryAsset.getId();
	}

	private Asset findOrSaveAsset(AssetRequest assetRequest) {
		String code = assetRequest.getCode();
		Asset foundAsset = assetRepository.findByCode(code);

		if (Objects.isNull(foundAsset)) {
			Asset asset = new Asset(
				assetRequest.getCode(),
				assetRequest.getName(),
				assetRequest.getPrice()
			);
			foundAsset = assetRepository.save(asset);
		}

		return foundAsset;
	}
}
