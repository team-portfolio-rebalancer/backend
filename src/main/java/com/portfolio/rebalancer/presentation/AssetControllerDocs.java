package com.portfolio.rebalancer.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.portfolio.rebalancer.dto.request.AssetRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "자산")
public interface AssetControllerDocs {

	@Operation(summary = "자산 생성")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "자산 생성 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "코드가 입력되지 않음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_001",
					        "message": "입력값이 없습니다."
					    },
					    "data": null
					}
					"""),
				@ExampleObject(name = "이름이 입력되지 않음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_001",
					        "message": "입력값이 없습니다."
					    },
					    "data": null
					}
					"""),
				@ExampleObject(name = "가격이 입력되지 않음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_001",
					        "message": "입력값이 없습니다."
					    },
					    "data": null
					}
					"""),
			}))
	})
	ResponseEntity<Void> create(@RequestBody final AssetRequest request);
}
