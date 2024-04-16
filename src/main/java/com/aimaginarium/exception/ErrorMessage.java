package com.aimaginarium.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public String USER_NOT_FOUND = "User [%s] not found";
    public String USER_DETAILS_NOT_FOUND = "User details by user [%s] not found";
    public String USER_DETAILS_BY_ID_NOT_FOUND = "User details [%s] not found";
}
