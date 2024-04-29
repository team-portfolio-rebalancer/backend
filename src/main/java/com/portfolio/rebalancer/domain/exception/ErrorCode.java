package com.portfolio.rebalancer.domain.exception;

public interface ErrorCode {

    int getStatusCode();

    String getErrorCode();

    String getMessage();
}
