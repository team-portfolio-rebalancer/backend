package com.portfolio.rebalancer.presentation;

import com.portfolio.rebalancer.domain.exception.RebalancerException;
import com.portfolio.rebalancer.dto.request.RequestErrorCode;
import com.portfolio.rebalancer.dto.response.RebalancerResponse;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RebalancerResponse<Void>> handleInvalidRequest(final BindingResult bindingResult) {
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        final FieldError mainError = fieldErrors.get(0);
        String[] splitError = RequestErrorCode.splitCodeAndMessage(
                Objects.requireNonNull(mainError.getDefaultMessage()));

        RebalancerResponse<Void> response = RebalancerResponse.fail(splitError[0], splitError[1]);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RebalancerException.class)
    public ResponseEntity<RebalancerResponse<Void>> handleIllegalArgumentException(
            final RebalancerException exception) {
        RebalancerResponse<Void> response = RebalancerResponse.fail(exception.getErrorCode(), exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }
}
