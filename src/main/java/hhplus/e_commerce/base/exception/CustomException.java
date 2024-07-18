package hhplus.e_commerce.base.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception {
    public enum ExceptionType {
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
