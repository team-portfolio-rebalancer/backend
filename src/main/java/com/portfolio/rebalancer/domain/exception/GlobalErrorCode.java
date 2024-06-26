package com.portfolio.rebalancer.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    NOT_CONTROLLED_ERROR(400, "GLOBAL_001", "다뤄지지 않은 에러입니다."),
    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;
}
