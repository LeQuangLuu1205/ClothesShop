package com.luu.BackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1004, "User not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(1005, "Category not found", HttpStatus.NOT_FOUND),
    IMAGE_SAVE_FAILED(1006, "Failed to save image", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST(1007, "Invalid request", HttpStatus.BAD_REQUEST),
    NAME_CANNOT_BE_EMPTY(1008, "Name cannot be empty", HttpStatus.BAD_REQUEST),
    PRICE_CANNOT_BE_NULL(1009, "Price cannot be null", HttpStatus.BAD_REQUEST),
    DISCOUNT_CANNOT_BE_NULL(1010, "Discount cannot be null", HttpStatus.BAD_REQUEST),
    QUANTITY_CANNOT_BE_NULL(1011, "Quantity cannot be null", HttpStatus.BAD_REQUEST),
    DESCRIPTION_CANNOT_BE_EMPTY(1012, "Description cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_DATA(1014, "Invalid data", HttpStatus.BAD_REQUEST),
    CATEGORY_ID_CANNOT_BE_NULL(1013, "Category ID cannot be null", HttpStatus.BAD_REQUEST);
    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}

