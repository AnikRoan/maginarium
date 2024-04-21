package com.aimaginarium.utils;


public record EndpointUris() {
    public static final String ROOT_USER = "/api/v1/users";
    public static final String GET_USER = "/get/{id}";
    public static final String GET_PROFILE = "/getProfile/{id}";
    public static final String DELETE_USER = "/delete/{id}";
    public static final String CHANGE_EMAIL = "/changeEmail/{id}";
    public static final String CHANGE_NUMBER = "/changePhoneNumber/{id}";
    public static final String CHANGE_USERNAME = "/changeUsername/{id}";
    public static final String CHANGE_PASSWORD = "/changePassword/{id}";
    public static final String ROOT_GALLERY = "/v1/gallery";
    public static final String GET_GALLERY = "/{id}";
    public static final String GET_GALLERY_BY_USER = "/user/{userId}";
    public static final String UPDATE_GALLERY = "/update/{id}";
}
