package com.portfolio.rebalancer.domain.categoryasset;

import com.portfolio.rebalancer.domain.asset.Asset;
import com.portfolio.rebalancer.domain.category.Category;
import com.portfolio.rebalancer.domain.color.Color;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_asset")
@NoArgsConstructor
@Getter
public class CategoryAsset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private Asset asset;

	@Column(nullable = false)
	private Long amount;

	@Column(nullable = false)
	private Integer aimPercentage;

	@Embedded
	private Color color;

	public CategoryAsset(
		final Category category,
		final Asset asset,
		final Long amount,
		final Integer aimPercentage,
		final String color
	) {
		this.category = category;
		this.asset = asset;
		this.amount = amount;
		this.aimPercentage = aimPercentage;
		this.color = new Color(color);
	}
}
