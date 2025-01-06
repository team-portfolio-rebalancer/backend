package com.portfolio.rebalancer.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

@SpringBootTest
public class AssetServiceTest {

	@Autowired
	private AssetService assetService;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
	}

	@DisplayName("자산을 저장한다.")
	@Test
	void 자산_생성() {
		// given
		AssetRequest request = new AssetRequest("360200", "ACE 미국S&P500", 16558L);

		// when
		Long saveId = assetService.save(request);

		// then
		assertThat(saveId).isNotNull();
	}
}
