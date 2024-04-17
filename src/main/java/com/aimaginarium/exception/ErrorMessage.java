package com.aimaginarium.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public final String USER_NOT_FOUND = "User [%s] not found";
    public final String USER_DETAILS_NOT_FOUND = "User details by user [%s] not found";
    public final String INVALID_PASSWORD = "Wrong password";
}
