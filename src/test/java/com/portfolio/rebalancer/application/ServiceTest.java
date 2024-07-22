package com.portfolio.rebalancer.application;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.rebalancer.support.DatabaseCleanUp;

@SpringBootTest
public class ServiceTest {

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
	}
}
