package com.portfolio.rebalancer.acceptance;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.portfolio.rebalancer.dto.request.AssetRequest;
import com.portfolio.rebalancer.support.DatabaseCleanUp;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(properties = "spring.session.store-type=none", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssetAcceptanceTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseCleanUp.execute();
	}

	@DisplayName("사용자가 자산을 저장하고 200 OK를 반환한다.")
	@Test
	void create() {
		// given
		String code = "360200";
		String name = "ACE 미국S&P500";
		Long price = 16558L;
		AssetRequest assetRequest = new AssetRequest(code, name, price);

		// when
		ValidatableResponse response = RestAssured.given().log().all()
			.body(assetRequest)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().post("/assets")
			.then().log().all();

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}
}
