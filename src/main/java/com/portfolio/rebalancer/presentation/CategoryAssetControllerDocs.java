package com.portfolio.rebalancer.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.portfolio.rebalancer.dto.request.CategoryAssetRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "카테고리 자산")
public interface CategoryAssetControllerDocs {

	@Operation(summary = "카테고리 자산 생성")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "카테고리 자산 생성 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "카테고리 아이디가 입력되지 않음", value = """
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
				@ExampleObject(name = "색상이 입력되지 않음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_001",
					        "message": "입력값이 없습니다."
					    },
					    "data": null
					}
					"""),
				@ExampleObject(name = "색상 형식이 잘못됨", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_002",
					        "message": "색상 형식이 맞지 않습니다."
					    },
					    "data": null
					}
					"""),
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
				@ExampleObject(name = "보유 수량이 1보다 작음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_003",
					        "message": "보유 수량은 1 이상이어야 합니다."
					    },
					    "data": null
					}
					"""),
				@ExampleObject(name = "목표 비율이 0보다 작거나 100보다 큼", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_004",
					        "message": "목표 비율은 0 이상 100 이하이어야 합니다."
					    },
					    "data": null
					}
					"""),
			}))
	})
	ResponseEntity<Void> create(@RequestBody final CategoryAssetRequest request);
}
