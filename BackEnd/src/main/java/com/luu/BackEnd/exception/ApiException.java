package com.luu.BackEnd.exception;

import com.luu.BackEnd.enums.ErrorCode;

public class ApiException extends RuntimeException {



    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
//
//    public ApiException(ErrorCode errorCode, String message) {
//        super(message);
//        this.errorCode = errorCode;
//    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}