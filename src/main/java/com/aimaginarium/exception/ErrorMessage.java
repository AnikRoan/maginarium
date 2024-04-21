package com.aimaginarium.exception;


public record ErrorMessage() {

    public static final String PICTURE_NOT_FOUND = "Picture [%s] not found";
    public static final String PICTURE_DETAILS_NOT_FOUND = "Picture details [%s] not found";
    public static final String PICTURE_DELETED = "Picture [%s] is already deleted";
    public static final String PICTURE_UPDATE_EXCEPTION = "Picture [%s] can't be updated";
    public static final String WIDTH_RANGE_MESSAGE = "Width must be between 128 and 2048";
    public static final String USER_NOT_FOUND = "User [%s] not found";
    public static final String USER_DETAILS_NOT_FOUND = "User details by user [%s] not found";
    public static final String USER_DETAILS_BY_ID_NOT_FOUND = "User details [%s] not found";
    public static final String GALLERY_NOT_FOUND_BY_USER = "Gallery with user id=[%s] not found";
    public static final String GALLERY_NOT_FOUND = "Gallery with id=[%s] not found";
    public static final String GALLERY_ALREADY_EXIST = "Gallery already exist by user id=[%s]";
    public static final String TITLE_NOT_EMPTY = "Title must be filled";
}
