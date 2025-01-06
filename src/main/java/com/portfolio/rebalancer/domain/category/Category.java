package com.portfolio.rebalancer.domain.category;

import java.util.ArrayList;
import java.util.List;

import com.portfolio.rebalancer.domain.categoryasset.CategoryAsset;
import com.portfolio.rebalancer.domain.color.Color;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Category {

	@OneToMany(mappedBy = "category")
	private final List<CategoryAsset> assets = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private String name;

	@Embedded
	private Color color;

	public Category(final Long userId, final String name, final String color) {
		this.userId = userId;
		this.name = name;
		this.color = new Color(color);
	}

	public void update(final Category updatingCategoryInfo) {

		if (updatingCategoryInfo.getName() != null) {
			this.name = updatingCategoryInfo.getName();
		}

		if (updatingCategoryInfo.getColor() != null) {
			this.color = updatingCategoryInfo.getColor();
		}
	}
}
