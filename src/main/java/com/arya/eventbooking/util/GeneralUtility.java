package com.arya.eventbooking.util;

public class GeneralUtility {

    public static <T> GenericResponse<T> successResponse(String message, T data) {
        return new GenericResponse<>(Constant.SUCCESS, message, data);
    }

    public static <T> GenericResponse<T> failureResponse(String message, T data) {
        return new GenericResponse<>(Constant.FAILURE, message, data);
    }
}
