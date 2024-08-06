package hhplus.e_commerce.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception {
    public enum ExceptionType {
        /**
         * 유저 정보 없음
         */
        NO_USER_INFO,

        /**
         * 동일한 유저 정보 존재
         */
        HAS_SAME_USER,

        /**
         * 재고 부족
         */
        STOCK_SHORTAGE,

        /**
         * 잔액 부족
         */
        BALANCE_SHORTAGE
    }

    private final ExceptionType exceptionType;

    public CustomException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
