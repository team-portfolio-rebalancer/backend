package com.portfolio.rebalancer.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.portfolio.rebalancer.dto.request.CategoryRequest;
import com.portfolio.rebalancer.dto.response.CategoryResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "카테고리")
public interface CategoryControllerDocs {

	@Operation(summary = "카테고리 생성")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "카테고리 생성 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "유저 아이디가 입력되지 않음", value = """
					{
						"success": false,
						"error": {
							"code": "INPUT_001",
							"message": "회사명이 입력되지 않았습니다."
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
					""")
			}))
	})
	ResponseEntity<Void> create(@RequestBody final CategoryRequest request);

	@Operation(summary = "카테고리 상세 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "카테고리 상세 조회 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "아이디가 입력되지 않음", value = """
					{
						"success": false,
						"error": {
							"code": "INPUT_001",
							"message": "입력값이 없습니다."
						},
						"data": null
					}
					"""),
				@ExampleObject(name = "아이디 값이 잘못됨", value = """
					{
						"success": false,
						"error": {
							"code": "CATEGORY_001",
							"message": "해당 id의 카테고리가 없습니다."
						},
						"data": null
					}
					""")
			}))
	})
	ResponseEntity<CategoryResponse> findById(Long id);

	@Operation(summary = "카테고리 전체 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "카테고리 전체 조회 성공")
	})
	ResponseEntity<List<CategoryResponse>> findAll();
}
