package com.aimaginarium.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public final String PICTURE_NOT_FOUND = "Picture [%s] not found";
    public final String PICTURE_DETAILS_NOT_FOUND = "Picture details [%s] not found";
    public static final String PICTURE_DELETED = "Picture [%s] is already deleted";
    public static final String PICTURE_UPDATE_EXCEPTION = "Picture [%s] can't be updated";
    public static final String WIDTH_RANGE_MESSAGE = "Width must be between 128 and 2048";
}
