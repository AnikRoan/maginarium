package com.aimaginarium.exception;


public record ErrorMessage() {
    public static final String USER_NOT_FOUND = "User [%s] not found";
    public static final String USER_DETAILS_NOT_FOUND = "User details by user [%s] not found";
    public static final String INVALID_PASSWORD = "Wrong password";
    public static final String GALLERY_NOT_FOUND_BY_USER = "Gallery with user id=[%s] not found";
    public static final String GALLERY_NOT_FOUND = "Gallery with id=[%s] not found";
    public static final String GALLERY_ALREADY_EXIST = "Gallery already exist by user id=[%s]";
}
