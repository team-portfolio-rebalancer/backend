package com.portfolio.rebalancer.presentation;

import com.portfolio.rebalancer.dto.request.CategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "카테고리")
public interface CategoryControllerDocs {

    @Operation(summary = "카테고리 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "카테고리 생성 성공"),
            @ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
                    examples = {
                            @ExampleObject(name = "유저 아이디가 입력되지 않음", value = """
                                    {
                                        "message" : "입력값이 없습니다."
                                    }
                                    """),
                            @ExampleObject(name = "이름이 입력되지 않음", value = """
                                    {
                                        "message" : "입력값이 없습니다."
                                    }
                                    """),
                            @ExampleObject(name = "색상이 입력되지 않음", value = """
                                    {
                                        "message" : "입력값이 없습니다."
                                    }
                                    """),
                            @ExampleObject(name = "색상 형식이 잘못됨", value = """
                                    {
                                        "message" : "색상 형식이 맞지 않습니다."
                                    }
                                    """)
                    }))
    })
    ResponseEntity<Void> create(@RequestBody final CategoryRequest request);
}
