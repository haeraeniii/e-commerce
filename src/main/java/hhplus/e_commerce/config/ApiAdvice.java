package hhplus.e_commerce.config;

import hhplus.e_commerce.base.data.ApiResult;
import hhplus.e_commerce.base.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiAdvice {

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
}
