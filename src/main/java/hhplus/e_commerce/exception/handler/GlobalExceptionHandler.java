package hhplus.e_commerce.exception.handler;

import hhplus.e_commerce.base.data.ApiResult;
import hhplus.e_commerce.exception.CustomException;
import hhplus.e_commerce.exception.RestApiException;
import hhplus.e_commerce.exception.errorcode.CommonErrorCode;
import hhplus.e_commerce.exception.errorcode.ErrorCode;
import hhplus.e_commerce.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResult customException (CustomException e) {

        String message = "실패했습니다.";

        switch (e.getExceptionType()) {
            case NO_USER_INFO :
                message = "유저 정보가 없습니다.";
                break;
            case HAS_SAME_USER :
                message = "이미 가입된 회원입니다.";
                break;
            case STOCK_SHORTAGE :
                message = "재고가 부족합니다.";
                break;
            case BALANCE_SHORTAGE:
                message = "잔액이 부족합니다.";
        }

        return new ApiResult(false, message);
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleQuizException(final RestApiException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(final IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(final Exception ex) {
        log.warn("handleAllException", ex);
        final ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    private ErrorResponse makeErrorResponse(final ErrorCode errorCode) {
        return hhplus.e_commerce.exception.response.ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }


    private ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode, final String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }

    private hhplus.e_commerce.exception.response.ErrorResponse makeErrorResponse(final ErrorCode errorCode, final String message) {
        return hhplus.e_commerce.exception.response.ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }

    private ResponseEntity<Object> handleExceptionInternal(final org.springframework.validation.BindException e, final ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    private hhplus.e_commerce.exception.response.ErrorResponse makeErrorResponse(final org.springframework.validation.BindException e, final ErrorCode errorCode) {
        final List<hhplus.e_commerce.exception.response.ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(hhplus.e_commerce.exception.response.ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return hhplus.e_commerce.exception.response.ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }
}
