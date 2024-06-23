package com.portfolio.rebalancer.domain.asset;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	Asset findByCode(String code);
}
